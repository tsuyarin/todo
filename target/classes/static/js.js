{

    // ここにJavaScriptのコードを記述
  //初期カラー設定
    const hiratas = document.querySelectorAll(".hiratas");
       for(let i =0; i < hiratas.length; i++){
        let i2 = i % 6;
        switch(i2){
            case 0:
                hiratas[i].style.color = "deepskyblue"
                break;
            case 1:
                hiratas[i].style.color = "red"
                break;
            case 2:
                 hiratas[i].style.color = "pink"
                 break;
            case 3:
                hiratas[i].style.color = "green"
                break;
            case 4:
                hiratas[i].style.color = "gold"
                break;
            case 5:
                hiratas[i].style.color = "blue"
                break;
            }        
        }
        //星の表示
        star();
        //テキストカラーアニメ
        loopAnime();

 }   



function loopAnime(){
    const hiratas = document.querySelectorAll(".hiratas");  
    for(let i =0; i < hiratas.length; i++){
        switch(hiratas[i].style.color){
            case "blue":
                hiratas[i].style.color = "deepskyblue"
                break;
            case "deepskyblue":
                hiratas[i].style.color = "red"
                break;
            case "red":
                hiratas[i].style.color = "pink"
                break;
            case "pink":
                hiratas[i].style.color = "green"
                break;
            case "green":
                hiratas[i].style.color = "gold"
                break;
            case "gold":
                hiratas[i].style.color = "blue"
                break;
            }    
        }
        setTimeout(loopAnime, 1000);
      }

function star(){
    for(let i = 0; i < 400; i++) {
        let x = Math.random() * 100;
        let y = Math.random() * 50;
        let d = Math.random() * 4;
        let s = Math.random() * 2 + 1.5;
        //create new element and add to html
        let star = document.createElement("div");
        star.classList.add("star");
        let sky = document.getElementById("starry_sky");
        sky.appendChild(star);
      
        star.style.width = d + "px";
        star.style.height = d + "px";
        star.style.top = y + "%";
        star.style.left = x + "%";
        star.style.animationDuration = s + "s";
      }
}
