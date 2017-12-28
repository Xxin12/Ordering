/**
 * 替換字符串第一個匹配字符
 *
 * @param str 需要替換的字符串
 * @param regex 匹配字符串
 * @param replacement 匹配字符串替換後的字符串
 * @returns {string} 替換後的字符串
 */
function replaceFirst(str, regex, replacement) {
	var index = str.indexOf(regex);
	if (index != -1) {
		return str.substr(0, index) + replacement + str.substr(index + 1, str.length);
	}
	return str;
}

/**
 * 拼接 URL 參數
 *
 * @param paramMap URL 集合
 * @returns {string} URL 參數
 */
function mosaicUrlParam(paramMap) {
	var result = "";
	if (paramMap != null && paramMap != undefined && typeof paramMap == "object") {
		$.each(paramMap, function (key, value) {
			result += "&" + key + "=" + value;
		});
	}
	return replaceFirst(result, "&", "?");
}

var requestType = {};
requestType.GET = "GET";
requestType.DELETE = "DELETE";
requestType.POST = "POST";
requestType.PUT = "PUT";

var dataType = {};
dataType.JSON = "json";
dataType.TEXT = "text";

/**
 * 發送請求
 *
 * @param url 請求地址
 * @param type 請求類型，"GET" 或者 "POST"
 * @param dataType 返回值類型
 * @param paramMap 請求參數
 * @param successFunction 成功後執行的回調函數
 */
function request(url, type, dataType, paramMap, successFunction) {
	if (type == requestType.GET || type == requestType.DELETE) {
		$.ajax({
			url: url + mosaicUrlParam(paramMap),
			dataType: dataType,
			type: type,
			async: false,
			success: successFunction
		});
	} else if (type == requestType.POST || type == requestType.PUT) {
		$.ajax({
			url: url,
			type: type,
			dataType: dataType,
			data: paramMap,
			async: false,
			success: successFunction
		});
	}
}

/**
 * 發送跨域請求
 *
 * @param url 請求地址
 * @param type 請求類型，"GET" 或者 "POST"
 * @param dataType 返回值類型
 * @param paramMap 請求參數
 * @param successFunction 成功後執行的回調函數
 */
function crossDomainRequest(url, type, paramMap, successFunction) {
	if (type == requestType.GET || type == requestType.DELETE) {
		$.ajax({
			url: url + mosaicUrlParam(paramMap),
			dataType: "jsonp",
			type: type,
			async: false,
			jsonp: 'callback',
			success: successFunction
		});
	} else if (type == requestType.POST || type == requestType.PUT) {
		$.ajax({
			url: url,
			type: type,
			dataType: "jsonp",
			data: paramMap,
			async: false,
			jsonp: 'callback',
			success: successFunction
		});
	}
}

// =========================================

/**
 * 生成固定或者不固定位數的隨機數
 *
 * @param isFixedLength 是否固定位數
 * @param min 如果非固定位數，則為最小位數
 * @param max 最大位數
 * @returns {string} 隨機數
 */
function randomStr(isFixedLength, min, max) {
	var str = "",
		range = min,
		arr = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];

	if (isFixedLength) {
		range = Math.round(Math.random() * (max - min)) + min;
	}
	for (var i = 0; i < range; i++) {
		pos = Math.round(Math.random() * (arr.length - 1));
		str += arr[pos];
	}
	return str;
}

// =========================================

Date.prototype.getFullMonth = function () {
	var MM = this.getMonth() + 1;
	return MM > 10 ? MM : '0' + MM;
};

Date.prototype.getFullDate = function () {
	var dd = this.getDate();
	return dd > 10 ? dd : '0' + dd;
};

Date.prototype.getFullHours = function () {
	var HH = this.getHours();
	return HH >= 10 ? HH : '0' + HH;
};

Date.prototype.getFullMinutes = function () {
	var mm = this.getMinutes();
	return mm >= 10 ? mm : '0' + mm;
};

Date.prototype.getFullSeconds = function () {
	var ss = this.getSeconds();
	return ss >= 10 ? ss : '0' + ss;
};

/**
 * 將 Date 類型對象格式化為字符串
 *
 * @param date 需要格式化的 Date 類型對象
 * @param format 需要格式化的格式
 * @returns {string} 格式化后的日期
 */
function formatDate(date, format) {
	var yyyy = date.getFullYear();

	var MM = date.getFullMonth();

	var dd = date.getFullDate();

	var HH = date.getFullHours();

	var mm = date.getFullMinutes();

	var ss = date.getFullSeconds();

	return format.replace("yyyy", yyyy).replace("MM", MM).replace("dd", dd).replace("HH", HH).replace("mm", mm).replace("ss", ss);
}

/**
 * 獲取月的最後一天
 *
 * @returns {Date} 月的最後一天的 Date 對象
 */
Date.prototype.getLastDate = function () {
	var newYear = this.getFullYear();
	var nextMonth = this.getMonth() + 1;
	if (nextMonth > 12) {
		newYear++;
		nextMonth = 1;
	}
	var nextMonthFirstDay = new Date(newYear, nextMonth, 1);
	return new Date(nextMonthFirstDay.getTime() - 1000 * 60 * 60 * 24).getFullDate();
};

