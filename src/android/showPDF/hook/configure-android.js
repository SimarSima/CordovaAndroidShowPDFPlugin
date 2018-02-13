/**
 * Created by p.messina on 14/10/2015.
 */

module.exports = function (ctx) {

    if (ctx.opts.platforms.indexOf('android') < 0) {
        return;
    }
    var fs = ctx.requireCordovaModule('fs'),
        path = ctx.requireCordovaModule('path'),
        deferral = ctx.requireCordovaModule('q').defer();

    function replace_string_in_file(filename, to_replace, replace_with) {
        var data = fs.readFileSync(filename, 'utf8');
        var result = data.replace(new RegExp(to_replace, "g"), replace_with);
        fs.writeFileSync(filename, result, 'utf8');
    }
    
    function getConfidId(configString){
    	
    	var firstCut = configString.split(" id=");
		//console.log(firstCut);
		var secondCut = firstCut[1].replace(/"/g,"");
		//console.log(secondCut);
		var id = secondCut.slice(0,secondCut.indexOf(" "));
		//console.log(id);
		return id;
    }
    
    var ourconfigfile = path.join(ctx.opts.projectRoot, "config.xml");
    var configXMLPath = "config.xml";
    var data = fs.readFileSync(ourconfigfile, 'utf8');
    
    var replaceWith = getConfidId(data) + ".R";
    
    var platformRoot = path.join(ctx.opts.projectRoot, 'platforms/android');
    var fileImportR = [
		{filePath: 'app/src/java/cordova/plugin/androidShowPDFPlugin/Main.java', importStatement: 'simar.android.showpdf.R'},
		{filePath: 'app/src/java/cordova/plugin/androidShowPDFPlugin/androidShowPDFPlugin.java', importStatement: 'simar.android.showpdf.R'}
    ];


    console.log('*****************************************');
    console.log('*       inject file R  ANDROID             *');
    console.log('*****************************************');
    console.log('*       Inject: ' + replaceWith + '    *');
    
    fileImportR.forEach(function(val) {
    	var fullfilename = path.join(platformRoot, val.filePath);
    	console.log('*  Inject in file: ' + fullfilename + ' the import statemet: ' + val.importStatement + '  *');
    	if (fs.existsSync(fullfilename)) {
    		replace_string_in_file(fullfilename, val.importStatement, replaceWith);
    	} else {
            console.error('* missing file:', fullfilename);
        }
    });
}
