function zipCheck() {
	let url = "zipcheck.jsp?check=y";
	window.open(url, "post", "toolbar=no,width=500,height=400,top=200,left=100,status=yes,scrollbars=yes,menubar=no");
}

function idCheck() {
	if(regForm.id.value === "") {
		alert("ID를 입력하시오.");
		regForm.id.focus();
	} else {
		url = "idcheck.jsp?id=" + regForm.id.value;
		window.open(url, "id", "width=300,height=150,top=200,left=100");
	}
}

function inputCheck() {
	// 입력 자료 검사
	if(regForm.id.value === "") {
		alert("ID를 입력하시오.");
		regForm.id.focus();
		return;
	}
	// 이하 생략
	regForm.submit();
}


function funcLogin() {
	if(loginForm.id.value === "") {
		alert("회원 아이디를 입력하시오.");
		loginForm.id.focus();
	} else if(loginForm.passwd.value === ""){
		alert("회원 비밀번호를 입력하시오.");
		loginForm.passwd.focus();
	} else {
		loginForm.action="loginproc.jsp";
		loginForm.method="post";
		loginForm.submit();
	}
}

function funcNewMember() {
	location.href="register.jsp";
}





// ----------- 쇼핑몰 관련 ---------------
//쇼핑몰 로그인 후 자신의 정보 수정 시 사용
function memberUpdateOk(){
	//입력자료 오류검사 생략
	document.updateForm.submit();
}
function memberUpdateCancel(){
	location.href="../guest/guest_index.jsp";
}
function memberDelete(){
	alert("회원탈퇴는 곧 죽음!!!");
}

//관리자 관련 
function funcAdminLogin(){
	if(adminLoginform.adminid.value === ""){
		alert("id 를 입력하시오");
		adminLoginform.adminid.focus();
		return;
	}
	if(adminLoginform.adminpasswd.value === ""){
		alert("password를 입력하시오");
		adminLoginform.adminpasswd.focus();
		return;
	}
	adminLoginform.submit();
}
function funcAdminHome(){  // 메인 페이지로 돌아가는 메소드
	location.href = "../guest/guest_index.jsp";
}

//관리자 입장에서 각 회원 수정
function memUpdate(id) {
	//alert(id);
	document.updateFrm.id.value = id;
	document.updateFrm.submit();
}

function memberUpdateAdmin(){
	document.updateFormAdmin.submit();
}
function memberUpdateCancelAdmin(){
	location.href = "membermanager.jsp";
}

//관리자에서 상품처리
function productDetail(no){
	document.detailForm.no.value = no;
	document.detailForm.submit();
}
function productUpdate(no){
	if(confirm("정말 수정할까요?")){
		document.updateForm.no.value = no;
		document.updateForm.submit();
	}
}
function productDelete(no){
	if(confirm("정말 삭제할까요?")){
		document.delForm.no.value = no;
		document.delForm.submit();		
	}
}

//사용자에서 상품 처리
function productDetail_guest(no){ 
	document.detailFrm.no.value = no;
	document.detailFrm.submit();
}

//카트 처리용
function cartUpdate(form){
	form.flag.value = "update";
	form.submit();
}
function cartDelete(form){
	form.flag.value = "del";
	form.submit();
}

//관리자에서 주문 처리
function orderDetail(no){
	document.detailFrm.no.value = no;
	document.detailFrm.submit();
}

function orderUpdate(form){
	document.detailFrm.flag.value = "update";
	form.submit();
}

function orderDelete(form){
	document.detailFrm.flag.value = "delete";
	form.submit();
}
