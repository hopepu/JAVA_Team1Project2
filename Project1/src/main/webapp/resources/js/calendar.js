var start;
var end;
/*배열만들기-내림차순*/
function makeListASC(start, end){
	const list = [];
	var len = end-(start-1);
	for(var i=0;i<len;i++){
		var str=start;
		list[i].push=str.padStart(2,0);
		start++;
		return list;
	}
}
/*배열만들기-오름차순*/
function makeListDESC(start, end){
	const list = [];
	var len = end-(start-1);
	for(var i=0;i<len;i++){
		var str=end;
		list[i].push=str;
		end--;
		return list;
	}
}

/*년, 월 받아 일 달력 리스트 만들기*/
var userYear;
var userMonth;
var endDay;
function getYear(){
	userYear=document.getElementById("yearText");
}


function getMonth(){
	userMonth = document.getElementById("monthText");

	var leapYear = (userYear % 4 == 0 || userYear % 100 == 0 || userYear % 400 == 0) ? true : false;
	switch(userMonth){
	case 1, 3, 5, 7, 8, 10, 12:
			endDay = 31;
			break;
		case 4, 6, 9, 11:
			endDay = 30;
			break;
		case 2:
			if (leapYear) {// 윤년이면
				endDay = 29;
			} else {
				endDay = 28;
			}
			break;
		default:
			endDay = 31;
		}	
	return endDay;
}

function makeDays(endDay){
	var start = 1;
	const list = [];
	var len = endDay-(start-1);
	for(var i=0;i<len;i++){
		var str=start;
		list[i].push=str.padStart(2,0);
		start++;
		return list;
	}
}

/*리스트 보이기*/
document.write('<option value="');
for(let i=0; i<endDay;i++){
	document.write('')
}
