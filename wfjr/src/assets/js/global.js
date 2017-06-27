import $ from './jquery'

class Global {
    constructor(config) {
        this.server = config.server; 
    }
    // 验证浏览器
    validateBrowser() {

        let ua = navigator.userAgent;     
            ua = ua.toLowerCase();     
	    let match = /(webkit)[ \/]([\w.]+)/.exec(ua) ||     
	    /(opera)(?:.*version)?[ \/]([\w.]+)/.exec(ua) ||     
	    /(msie) ([\w.]+)/.exec(ua) ||     
	    !/compatible/.test(ua) && /(mozilla)(?:.*? rv:([\w.]+))?/.exec(ua) || [];     
	   //如果需要获取浏览器版本号：match[2]     
	    switch(match[1]){                 
	    case "msie":      //ie   
		    if (parseInt(match[2]) <= 8){   //<ie8  
		    	document.getElementsByTagName("body")[0].style.textAlign = "center"
		    	document.getElementsByTagName("body")[0].innerHTML="<h3>暂时不支持IE8.0及以下版本浏览器，请升级您的IE浏览器版本，或使用firefox、Google Chrome浏览器！</h3>";
		    }  
	      break;             
	     case "webkit":     //safari or chrome                 
	      break;             
	     case "opera":      //opera                 
	      break;                 
	     case "mozilla":    //Firefox                 
	      break;             
	     default:                     
	      break;                 
	    }
    }
    //获得cookie
    getCookie(c_name) {
        if (document.cookie.length > 0) {
            let c_start = document.cookie.indexOf(c_name + "=")
            if (c_start != -1) {
                c_start = c_start + c_name.length + 1
                let c_end = document.cookie.indexOf(";", c_start)
                if (c_end == -1)
                    c_end = document.cookie.length
                return unescape(document.cookie.substring(c_start, c_end))
            }
        }
        return ""
    }
    // 设置cookie
    setCookie(c_name, value, expiredays) {
        let exdate = new Date()
        exdate.setDate(exdate.getDate() + expiredays)
        document.cookie = c_name + "=" + escape(value)
                + ((expiredays == null) ? "" : ";expires=" + exdate.toGMTString())
    }
    // 返回可见区域高度
    getClientHeight() {
        return document.body.clientHeight;
    }
    // 返回可见区域高度
    getClientWidth() {
        return document.body.clientWidth;
    }

}

window.jr = new Global({
    server: {
        url:"http://192.168.1.4:8080/chinesejr"
    }
});
//debugger;
export default Global;





