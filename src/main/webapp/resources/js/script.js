/**
 * 
 */

var values = []
const loc = window.location;
const path= loc.host.concat(loc.pathname)
var checkboxes = null;
var checkVar = 1;
function getCheckedCheckboxesFor(checkboxName) {
	console.log("check function");
	values = [];
    checkboxes = document.querySelectorAll('input[name="' + checkboxName + '"]:checked');
    Array.prototype.forEach.call(checkboxes, function(el) {
        values.push(el.value);
    });
	if(values.length==0){
		document.getElementById("ModalTrigger").click();
	}else{
		postMethode();
	}
}

function checkAllorNone(){
	if (checkVar==1){
		checkAll();
		checkVar=0;
	}else{
		checkNone();
		checkVar=1;
	}
}

function checkAll() {
	let Allcheckboxes = document.getElementsByName('employee');
	for(var i=0, n=Allcheckboxes.length;i<n;i++) {
	    Allcheckboxes[i].checked = true;
	  }
}
function checkNone() {
	let Allcheckboxes = document.getElementsByName('employee');
	for(var i=0, n=Allcheckboxes.length;i<n;i++) {
	    Allcheckboxes[i].checked = false;
	  }
}
	
function postMethode(){
	console.log("post function");
	fetch('http://'+path+'/exportAll', {
		method: 'POST', 
		body: JSON.stringify({
	    	value: values}),
		headers: {
        "Content-Type": "application/json; charset=utf-8"
    	}
	}).then(res => 
    {
        downloadFile(res);
    });

}



async function downloadFile(fetchResult) {        
    var filename = fetchResult.headers.get('content-disposition').split('filename=')[1];
    var data = await fetchResult.blob();
    // It is necessary to create a new blob object with mime-type explicitly set
    // otherwise only Chrome works like it should
    const blob = new Blob([data], { type: data.type || 'application/octet-stream' });
    if (typeof window.navigator.msSaveBlob !== 'undefined') {
        // IE doesn't allow using a blob object directly as link href.
        // Workaround for "HTML7007: One or more blob URLs were
        // revoked by closing the blob for which they were created.
        // These URLs will no longer resolve as the data backing
        // the URL has been freed."
        window.navigator.msSaveBlob(blob, filename);
        return;
    }
    // Other browsers
    // Create a link pointing to the ObjectURL containing the blob
    const blobURL = window.URL.createObjectURL(blob);
    const tempLink = document.createElement('a');
    tempLink.style.display = 'none';
    tempLink.href = blobURL;
    tempLink.setAttribute('download', filename);
    // Safari thinks _blank anchor are pop ups. We only want to set _blank
    // target if the browser does not support the HTML5 download attribute.
    // This allows you to download files in desktop safari if pop up blocking
    // is enabled.
    if (typeof tempLink.download === 'undefined') {
        tempLink.setAttribute('target', '_blank');
    }
    document.body.appendChild(tempLink);
    tempLink.click();
    document.body.removeChild(tempLink);
    setTimeout(() => {
        // For Firefox it is necessary to delay revoking the ObjectURL
        window.URL.revokeObjectURL(blobURL);
    }, 100);
}