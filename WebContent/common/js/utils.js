/**
 * 获取项目根路径
 * 
 * @returns
 */
function getRootpath() {
	var ctx = window.location.pathname;
	var rootPath = ctx.substring(0, ctx.substr(1).indexOf('/') + 1);
	return rootPath;
}