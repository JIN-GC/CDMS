// 気象庁のAPIから東京(130000)の天気予報JSONファイルを取得
const url = "https://www.jma.go.jp/bosai/forecast/data/forecast/130000.json";
// JSONデータ取得後のデータ抽出
function formatWeather(weather){
    // JSON全体
    //	console.log(weather);
    // 個別に抽出（例：東京の今日の天気）
    const WeekChars = [ "日", "月", "火", "水", "木", "金", "土" ];
    let time = new Date(weather[0].timeSeries[0].timeDefines[0]);
    let date0 = (time.getMonth()+1) + "月" + time.getDate() + "日（" + WeekChars[(time.getDay())] + "）";
    let date1 = (time.getMonth()+1) + "月" + time.getDate() + "日（" + WeekChars[(time.getDay()+1)] + "）";
    let area = weather[0].timeSeries[0].areas[0].area.name;
    let tenki0 = weather[0].timeSeries[0].areas[0].weathers[0];
    let tenki1 = weather[0].timeSeries[0].areas[0].weathers[1];
    let winds0 = weather[0].timeSeries[0].areas[0].winds[0];
    let winds1 = weather[0].timeSeries[0].areas[0].winds[1];
    let waves0 = weather[0].timeSeries[0].areas[0].waves[0];
    let waves1 = weather[0].timeSeries[0].areas[0].waves[1];
    $("#banner_topic").append( `${area} の予報です。　本日　${date0} は ${tenki0}　でしょう。　風は ${winds0}　波は ${waves0}　でしょう。　明日　${date1} は ${tenki1}　でしょう。　風は ${winds1}　波は ${waves1}でしょう。　♩　`);
}
window.addEventListener("load", ()=>{
    fetch(url)
        .then( response => response.json() )
        .then( weather => formatWeather(weather));
});
/*
let url = "https://www.jma.go.jp/bosai/forecast/data/forecast/130000.json";
fetch(url).then(function(response) {return response.json();
}).then(function(weather_tokyo) {
console.log(weather_tokyo);
$("#banner_topic").append(weather_tokyo);
});

*/
