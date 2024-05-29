let xhr;
let checkFirst = loopSend = false;
let kbs;

function start() {
	//console.log("b");
	if (checkFirst === false) {  // input에서 입력이 시작되고 있다는 것을 의미(빈 상태에서 한 글자 입력)
		kbs = setTimeout("sendkeyword()", 800);	// 1초후 sendkeyword를 호출
		loopSend = true;
	}
}

function sendkeyword() {
	//console.log("a");

	//let keyWord = document.querySelector("#keyword").value(); 는 아래 행과 동일
	let keyWord = document.frm.keyword.value;
	//console.log(keyWord);

	if (keyWord === "") hide();
	else {
		xhr = new XMLHttpRequest();
		let para = "keyword=" + keyWord;  // 사람의 이름(성씨)에 해당
		xhr.open("get", "jq8.jsp?" + para, true);
		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4) {
				if (xhr.status === 200) {
					process();
				}
			}
		}
		
		xhr.send(null);
	}
	clearTimeout(kbs);  // clearTimeout() : setTimeout() 설정 해제를 위한 메소드
}

function process() {
	let resultData = xhr.responseText;
	//console.log(resultData);
	let result = resultData.split("|");
	//console.log(`건수 : ${result[0]} 자료 : ${result[1]}`);
	let tot = result[0];
	if(tot > 0) {
		let datas = result[1].split(",");
		let imsi = "";
		for(let i = 0; i< datas.length; i++) {
			imsi += "<a href=\"javascript:func('" + datas[i] + "')\">" + datas[i] + "</a><br>";
		}
		console.log(imsi);
		document.querySelector("#suggestList").innerHTML = imsi;
		show();
	}
}

function func(reData) {
	alert(reData);
	frm.sel.value = reData;
	loopSend = checkFirst = false;
	hide();
	
	frm.keyword.value = "";
}

function hide() {
	document.querySelector("#suggest").style.display = "none";
}

function show() {
	document.querySelector("#suggest").style.display = "";
}