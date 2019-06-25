

//myfun.js  ===> <script></script>태그 사이에 들어가는 자바스크립트 코드!!
// ---> 변수선언, 초기화, 함수정의

     su1=11;//전역변수
 var su2=22;//전역변수

function lime(){
	 su3=33;//전역변수
 var su4=44;//지역변수!!(lime()함수 지역에서만 사용가능)
    alert('라임~!!');
    alert('su2='+su2+", su4="+ su4);
    //su1,su2,su3,su4 전부 사용가능
    
}

function plus(su1,su2){
	return su1+su2;
}//plus