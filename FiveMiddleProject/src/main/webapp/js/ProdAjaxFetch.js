/**
 *	전체상품조회 
 */

console.log('테스트중')

const showFields = {
	productName: "강의이름" ,
	productPrice: "가격" ,
	productExplain:"한줄소개",
	productThumbnailUrl:"썸네일",

}

//리스트출력!
fetch('prodListAjax.do')
.then((result)=> result.json())//steam->object
.then((data)=>{
	console.log(data);
})
.catch(function(err){
	console.log(err);
})
