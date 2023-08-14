// 安全or危険の表示用のIDを指定
const viewSafetyMessage = document.getElementById("safety");
const viewDangerMessage = document.getElementById("danger");
const viewAfterCalculation = document.getElementById("aftercalculation");
const viewChangeToItem = document.getElementById("ChangeToItem");

// 電源タップのコンセント数を取得し、電化製品のセレクトボックスを表示する
function getON(powerstrips, items) {

  // デフォルトで非表示
  viewSafetyMessage.style.display = "none";
  viewDangerMessage.style.display = "none";

  viewAfterCalculation.style.display = "none";

  // JSONの処理
  const ps = JSON.parse(powerstrips)
  const obj = JSON.parse(items);

  // 電源タップのIDを取得
  let powerstrip_id = document.querySelector('select').value;
  let powerstrip = [];
  // 選択したIDと一致するデータを探索、配列にデータを代入
  for (let i = 0; i < ps.length; i++) {
    if (ps[i].ps_id == powerstrip_id) {
      outllet_number = ps[i].outllet_number;
      powerstrip = [ps[i].ps_maker_name, ps[i].ps_name, ps[i].ps_code, ps[i].outllet_number, ps[i].watt];
    }
  }

  var str = "";
  // コンセント数分のセレクトボックスを表示
  for (let i = 0; i < powerstrip[3]; i++) {
    str += "<select id='itemselect" + i + "' name='itemselect" + i + "'>";
    for (let j = 0; j < obj.length; j++) {
      str += "<option value =" + obj[j].watt + ">" + obj[j].item_maker_name + " / " + obj[j].item_name + " / " + obj[j].item_code + "</option>";
    }
    str += "<option value = 0>空</option>";
    str += "</select>";
  }
  //電源タップのデータをhiddenで準備
  str += "<input id='ps_maker_name' type='hidden' name='ps_maker_name' value=" + powerstrip[0] + ">";
  str += "<input id='ps_name' type='hidden' name='ps_name' value=" + powerstrip[1] + ">";
  str += "<input id='ps_code' type='hidden' name='ps_code' value=" + powerstrip[2] + ">";
  str += "<input id='outllet_number' type='hidden' name='outllet_number' value=" + powerstrip[3] + ">";
  // これまでのstrをinnerHTMLで表示
  document.getElementById('item').innerHTML = str;
}

// 選択した電源タップ、電化製品から電力を計算する
function calculation() {

  viewAfterCalculation.style.display = "block";
  // デフォルトで非表示
  viewSafetyMessage.style.display = "none";
  viewDangerMessage.style.display = "none";
  viewChangeToItem.style.display = "none";
  // getON()でhiddenに格納したデータを取得
  let ps_maker_name = document.getElementById('ps_maker_name').value;
  let ps_name = document.getElementById('ps_name').value;
  let ps_code = document.getElementById('ps_code').value;
  let outllet_number = document.getElementById('outllet_number').value;
  // 合計電力量を準備
  let sum_watt = 0;
  // 電化製品の選択結果を取得し、sum_wattを計算する
  const watt = document.querySelector('input').value;
  for (let i = 0; i < outllet_number; i++) {
    let item_data = Number(document.getElementById('itemselect' + i).value);
    sum_watt += item_data;
  }
  // 使用可能電力量を計算
  let can_use_watt = watt - sum_watt;

  if (can_use_watt >= 0) {
    viewChangeToItem.style.display = "block";
    viewSafetyMessage.style.display = "block";
    viewDangerMessage.style.display = "none";
    document.getElementById("safety").innerHTML = "あと " + can_use_watt + " W 使用できます";

    // 計算結果登録用のテキストボックス等をinnerHTMLで表示
    let reg_maker_name = "メーカー名<input type='text' name='item_maker_name' value=" + ps_maker_name + ">";
    let reg_name = "商品名<input type='text' name='item_name' value=" + ps_name + ">";
    let reg_code = "商品コード<input type='text' name='item_code' value=" + ps_code + ">";
    let reg_ampere_number = "アンペア数<input type='number' step='0.1' name='ampere' value=" + sum_watt / 100 + ">";
    let reg_watt_number = "ワット数<input type='number' name='watt' value=" + sum_watt + ">";
    let btn = "<button type='submit'>登録</button>";
    document.getElementById("maker_name").innerHTML = reg_maker_name;
    document.getElementById("name").innerHTML = reg_name;
    document.getElementById("code").innerHTML = reg_code;
    document.getElementById("ampere_number").innerHTML = reg_ampere_number;
    document.getElementById("watt_number").innerHTML = reg_watt_number;
    document.getElementById("btn_after_register").innerHTML = btn;
  } else {
    viewSafetyMessage.style.display = "none";
    viewDangerMessage.style.display = "block";
    document.getElementById("danger").innerHTML = "あと <a style='color:red'>" + -can_use_watt + "</a>W 減らしてください";
  }
  document.getElementById("usedwatt").innerHTML = sum_watt;
}
