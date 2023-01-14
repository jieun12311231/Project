/**
 * 
 */

console.log('AjaxproductList 연결');
const showFeilds = { //보여주고 싶은 필드 변수  key:value형식
    productNum: "강의번호",
    productName: "강의이름",
    productTrainer: "강사명",
    productPrice: "가격"

};
//테이블 생성
function makeTable(aryData = [], parent) { //data의건수 만큼배열모양의 값 => 객체들이 넘어옴 
    //변수 선언
    let tbl = document.createElement('table');
    tbl.setAttribute('border', '1');
    let thd = document.createElement('thead');
    let tbd = document.createElement('tbody');
    let tr = document.createElement('tr');

    //thead영역
    // let fields = aryData[0]; //보여주는 항목을 지적(전체 항목) ex)attechId noticeDate noticeFile
    let fields = showFeilds; // 보여주고 싶은 필드만 볼수있게 
    for (const field in fields) {
        let th = document.createElement('th');
        th.innerText = fields[field]; //fields[field] : fields가 가지고 있는 field를 thead로 보여줄수있음  //th에 field를 넣어줌 
        tr.append(th); //반복문에 의해서 생성되는 th을 tr에 붙여줌 
    }

    //삭제, 수정 th추가 
    for (const col in addColumn) { //addColumn여기 들어있는 값만큼 컬럼을 추가함 
        let th = document.createElement('th');
        // th.innerText = addColumn[col][1]   //col = col1, col2 =>addColumn이 가지고 있는 col을 하면 배열을 의미하고 col안에서 'button'이아니고 삭제, 수정이 필요하기때문에 두번째 요소를 가지고 오기위해[1]
        th.innerHTML = addColumn[col][0] == 'button' ? addColumn[col][1] : '<input type="checkbox">'; //addColumn[col] : ['button','삭제']
        //삼항연산자 th.innerHTML = addColumn[col][0] 얘가 버튼이면 => addColumn[col][1] /버튼이 아니면 '<input type="checkbox">' input태그 속성이checkbox인것을 넣겠습니다
        tr.append(th);
    }

    thd.append(tr); //tr을 thd에 붙임 
    tbl.append(thd); // 테이블에 thead를 붙임 

    //boby영역
    //aryData건수 만큼 td생성, tbody에 붙여줌
    // aryData.forEach(data => {
    // let td = document.createElement('td');
    // td.innerText = data;
    // tr.append(td);
    for (const data of aryData) { //aryData의 data의 개수만큼 for문을 돌려서 tr생성
        let tr = makeTr(data); //새로 정의한 함수를 tr변수에 담에 줘야함 
        tbd.append(tr);
    }
    tbl.append(tbd);
    parent.append(tbl); // 최종적으로 생성된 테이블 요소를 매개 값으로 들어온 위치에 보여주기 위해서 append.


}
//tr만드는 함수 
function makeTr(data) {
    console.log(data)
    tr = document.createElement('tr');
    // showFeilds: 보여주고 싶은 필드만 볼수있게 위에 선언해놓은 변수임.
    for (const field in showFeilds) { // fields = showFeilds 이고, 그안에 값 하나하나가 field임!!  =>fields의 개수만큼 for문이 돌아서 field를 출력해서 td가 되는것
        let td = document.createElement('td');
        td.innerText = data[field];
        tr.append(td);
    }
}
fetch('AjaxproductList.do')
.then(result => result.json()) 
.then((data) => {
    console.log('연결')
    const parentEl = document.getElementById('show');
  //table생성하기 
    makeTable(data, parentEl);
})
.catch(function (err) { //익명함수가 false일 경우 실행될 함수 
    console.log(err);
})