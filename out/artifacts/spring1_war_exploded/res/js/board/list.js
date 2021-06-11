const frmElem=document.querySelector('#frm');
const selectElem= frmElem.recordCnt;

frmElem.recordCnt.addEventListener('change',function(){
	console.log(selectElem.value);
	frmElem.page.value=1;
	frmElem.submit();
});
function moveToDetail(iboard){
	location.href = 'detail?iboard='+iboard;
}