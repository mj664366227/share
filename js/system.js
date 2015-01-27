function get_user_os() { 
	var sUserAgent = navigator.userAgent.toLowerCase(); 
	var isWeiXin = sUserAgent.indexOf('micromessenger') > -1;
	if(isWeiXin) {
		return 'weixin';
	}
	var sPlatform = navigator.platform.toLowerCase();
	var isWin = sPlatform.indexOf('win') > -1;
	var isMac = sPlatform.indexOf('mac') > -1 || sPlatform.indexOf('iphone') > -1 || sPlatform.indexOf('ipad') > -1 || sPlatform.indexOf('ipod') > -1; 
	if (isMac) {
		var isIphone = sPlatform.indexOf('iphone') > -1;
		var isIpad = sPlatform.indexOf('ipad') > -1;
		var isIpod = sPlatform.indexOf('ipod') > -1;
		if(isIphone) {
			return 'iphone';
		} if(isIpad) {
			return 'ipad';
		} if(isIpod) {
			return 'ipod';
		} else {
			return 'mac'; 
		}
	}
	var isUnix = (sPlatform.indexOf('x11') > -1) && !isWin && !isMac; 
	if (isUnix) {
		return 'unix'; 
	}
	var isLinux = sPlatform.indexOf('linux') > -1; 	
	if (isLinux) {
		var bIsAndroid = sUserAgent.match(/android/i) == 'android' || sPlatform.indexOf('arm') > -1;
		if(bIsAndroid) {
			return 'android';
		} else { 
			return 'linux';
		}
	}
	if (isWin) { 
		var isWin2K = sUserAgent.indexOf('windows nt 5.0') > -1 || sUserAgent.indexOf('windows 2000') > -1; 
		if (isWin2K) {
			return 'win2000'; 
		}
		var isWinXP = sUserAgent.indexOf('windows nt 5.1') > -1 || 
		sUserAgent.indexOf('windows xp') > -1; 
		if (isWinXP) {
			return 'winxp'; 
		}
		var isWin2003 = sUserAgent.indexOf('windows nt 5.2') > -1 || sUserAgent.indexOf('windows 2003') > -1; 
		if (isWin2003) {
			return 'win2003'; 
		}
		var isWinVista = sUserAgent.indexOf('windows nt 6.0') > -1 || sUserAgent.indexOf('windows vista') > -1; 
		if (isWinVista) {
			return 'winvista'; 
		}
		var isWin7 = sUserAgent.indexOf('windows nt 6.1') > -1 || sUserAgent.indexOf('windows 7') > -1; 
		if (isWin7) {
			return 'win7'; 
		}
		var isWin8 = sUserAgent.indexOf('windows nt 6.2') > -1 || sUserAgent.indexOf('windows 8') > -1; 
		if (isWin8) {
			return 'win8'; 
		}
		var isWin8_1 = sUserAgent.indexOf('windows nt 6.3') > -1 || sUserAgent.indexOf('windows 8.1') > -1; 
		if (isWin8_1) {
			return 'win8.1'; 
		}
	} 
	return 'unknow'; 
}

function QueryString(item) {
        var svalue = location.search.match(new RegExp("[\?\&]" + item + "=([^\&]*)(\&?)", "i"));
        return svalue ? svalue[1] : svalue;
  }