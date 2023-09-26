// 気象庁のAPIから東京(130000)の天気予報JSONファイルを取得
const url = "https://www.jma.go.jp/bosai/forecast/data/forecast/130000.json";

// JSONデータ抽出・出力
function formatWeather(weather){
    const WeekChars = [ "日", "月", "火", "水", "木", "金", "土" ];
    //	JSON全体
    //	console.log(weather);
    //	JSONデータより、個別情報抽出（例：東京の今日の天気）
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
    //	header.jsp の　id="banner_topic"　の要素（文字列）に変数の情報追加
    $("#banner_topic").append( `${area} の予報です。　本日　${date0} は ${tenki0}　でしょう。　風は ${winds0}　波は ${waves0}　でしょう。　明日　${date1} は ${tenki1}　でしょう。　風は ${winds1}　波は ${waves1}でしょう。　♩　`);
}

//	JSONデータ取得
window.addEventListener("load", ()=>{
	//	JSONデータ取得
    fetch(url)
        .then( response => response.json() )
        .then( weather => formatWeather(weather));
});
/*
thenメソッドでfetchメソッドで指定されたリソース（url）の非同期通信結果をResponseオブジェクトを含むPromiseオブジェクトで返す。
thenメソッドでPromiseオブジェクト経由で渡されたResponseオブジェクトで生成された値weatherをformatWeather()関数で処理する
* JavaScriptにおいて、Promiseは非同期処理を扱うためのビルディングブロックであり、非同期性のオブジェクトです。
*/