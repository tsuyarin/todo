{
	
	window.onload = function() {
    createBingo();
};
	let number = [];
	let binnum = [];
	let binnum2 = [];
	let bingoCard; 
	let bingoNumber = [];
	let kaisu = 0;
	let bingo = true;
//ビンゴを生成する
function createBingo(){
	 bingoCard = [
    [false, false, false, false, false],
    [false, false, false, false, false],
    [false, false, true, false, false],
    [false, false, false, false, false],
    [false, false, false, false, false]
	];
	bingoNumber = [];
	number = [];
	binnum = [];
	binnum2 = [];
	kaisu = 0;
	bingo = true;
	let drawN = document.querySelector(".drawNumber");
	drawN.textContent = "**";
	let drawC = document.querySelector(".clear");
	drawC.style.visibility = 'hidden';
	let drawK = document.querySelector(".bingonum");
	drawK.textContent = 0;
	
	for(i=0; i<5; i++){
		number[i] = [];
		for(j=0; j<15; j++){
			number[i][j] = (15*i)+j+1;
		}
	}
	for(let i=0; i<5; i++){
		binnum[i] = [];
		for(j=0; j<5; j++){
			if(i==2&&j==2){
				binnum[i][j]=99;
			}else{
				let k = Math.floor(Math.random()*number[i].length);
				binnum[i][j]=number[i][k];
				number[i].splice(k,1);	
			}
		}
		
	}
	for(let i=0; i<5; i++){
		binnum2[i]=[];
		for(let j=0; j<5; j++){
			binnum2[i][j] = binnum[j][i];
		
		}
	}
	const bingoSheet = document.querySelector(".bingo_sheet");
	bingoSheet.innerHTML ='';
	for (let i = 0; i < 5; i++) {
        const row = document.createElement('tr');
        for (let j = 0; j < 5; j++) {
            const cell = document.createElement('td');
            (i==2&&j==2)?cell.textContent ="free":cell.textContent = binnum2[i][j];
            row.appendChild(cell);
        }
        bingoSheet.appendChild(row);
    }
    const maruSheet = document.querySelector(".maru_sheet");
    maruSheet.innerHTML='';
    for(let i=0; i<5; i++){
		const row = document.createElement('tr');	
		for(let j=0; j<5; j++){
			const cell = document.createElement('td');
			row.appendChild(cell);
		}
		maruSheet.appendChild(row);
	}
	
	
}


function drawBingo(){
	if(bingo){
	kaisu += 1;
	let drawK = document.querySelector(".bingonum");
	drawK.textContent = kaisu;

	let randomNumber;
	do{
		randomNumber = Math.floor(Math.random()*75)+1;
		
	}while(bingoNumber.includes(randomNumber));
	bingoNumber.push(randomNumber);
	//引いた数を表示する。
	let drawN = document.querySelector(".drawNumber");
	drawN.textContent = randomNumber;
	check(randomNumber);
	}
}

function check(num){
	for(let i=0; i<5; i++){
		for(let j=0; j<5; j++){
			if(binnum2[i][j]==num){		
				bingoCard[i][j]=true;
				maru(i,j);
				hantei();
				break;
			}
		}
	}
}

function maru(i,j){
	let drawMaru = document.querySelector(".maru_sheet");
	let row = drawMaru.rows[i];
	let cell = row.cells[j];
	cell.textContent="〇"
}

function hantei(){
//	横判定
	for(let i=0; i<5; i++){
		let hantei=true;
		for(let j=0; j<5; j++){
			if(bingoCard[i][j]!=true){
				hantei=false;
				break;
			}
		}
		if(hantei==true){
			clear();
			return;
		}		
	}


	
//	縦判定
	for(let i = 0; i < 5; i++){
		let hantei=true;
		for(let j = 0; j < 5; j++){
			if(bingoCard[j][i]!=true){
			hantei=false;
			break;	
			}		
		}
		if(hantei==true){
			clear();
			return;
		}
	}
	
//	斜め判定A
	let hantei=true;
	for(let i = 0; i < 5; i++ ){
		if(bingoCard[i][i]!=true){
			hantei=false;
			break;
		}
	}			
	if(hantei==true){
		clear();
		return;
	}
	
//	斜め判定B
	hantei=true;
	for(let i = 0; i < 5; i++ ){
		if(bingoCard[i][4-i]!=true){
			hantei = false;
			break;
		}
	}

	if(hantei==true){
		clear();
	}
}

function clear(){
	bingo=false;
	let drawC = document.querySelector(".clear");
	drawC.style.visibility = 'visible';
	
}
}