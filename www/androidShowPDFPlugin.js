var exec = require('cordova/exec');

var androidShowPDFPlugin={
	showPDF:function(arg0, success, error){
		exec(success, error, "androidShowPDFPlugin", "coolMethod", [arg0]);
	}
}
module.exports = androidShowPDFPlugin;