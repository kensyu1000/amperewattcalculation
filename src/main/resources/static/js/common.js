// 安全、危険の表示用のIDを指定
const viewSafetyMessage = document.getElementById("safety");
const viewDangerMessage = document.getElementById("danger");
const viewAfterCalculation = document.getElementById("aftercalculation");
const viewChangeToItem = document.getElementById("ChangeToItem");

// 電源タップのコンセント数を取得し、電化製品のセレクトボックスを表示する
function getON(powerstrips, items) {

  // デフォルトで非表示
  NotViewSafetyDanger();
  viewAfterCalculation.style.display = "none";

  // JSONの処理
  const ps = JSON.parse(powerstrips)
  const obj = JSON.parse(items);

  // 電源タップのIDを取得
  let powerstrip_id = document.querySelector('select').value;
  let powerstrip = [];
  // 選択したIDと一致するデータを探索、配列にデータを代入
  let j = 0;
  while (ps[j].ps_id != powerstrip_id) {
    j += 1;
  }
  powerstrip = [ps[j].ps_maker_name, ps[j].ps_name, ps[j].ps_code, ps[j].outllet_number, ps[j].watt];

  var str = "電化製品<br>";
  // コンセント数分のセレクトボックスを表示
  for (let i = 0; i < powerstrip[3]; i++) {
    str += "<select id='itemselect" + i + "' name='itemselect" + i + "'>";
    str += "<option value = 0>空</option>";
    for (let j = 0; j < obj.length; j++) {
      str += "<option value =" + obj[j].watt + ">" + obj[j].item_maker_name + " / " + obj[j].item_name + " / " + obj[j].item_code + "</option>";
    }
    str += "</select><br>";
  }
  //電源タップのデータをhiddenで準備
  str += "<input id='ps_maker_name' type='hidden' name='ps_maker_name' value=" + powerstrip[0] + ">";
  str += "<input id='ps_name' type='hidden' name='ps_name' value=" + powerstrip[1] + ">";
  str += "<input id='ps_code' type='hidden' name='ps_code' value=" + powerstrip[2] + ">";
  str += "<input id='outllet_number' type='hidden' name='outllet_number' value=" + powerstrip[3] + ">";
  str += "<input id='watt_number' type='hidden' name='watt_number' value=" + powerstrip[4] + ">";
  // これまでのstrをinnerHTMLで表示
  document.getElementById('item').innerHTML = str;
  document.getElementById("btn_calculation").style.display = "block";
}

// 選択した電源タップ、電化製品から電力を計算する
function calculation() {

  viewAfterCalculation.style.display = "block";

  // デフォルトで非表示
  NotViewSafetyDanger();
  viewChangeToItem.style.display = "none";

  // getON()でhiddenに格納したデータを取得
  let ps_maker_name = document.getElementById('ps_maker_name').value;
  let ps_name = document.getElementById('ps_name').value;
  let ps_code = document.getElementById('ps_code').value;
  let outllet_number = Number(document.getElementById('outllet_number').value);
  let watt_number = Number(document.getElementById('watt_number').value);

  let sum_watt = 0; //合計電力量

  // コンセントと電源タップ、どちらのワットを適用するか判定
  let watt;
  const outllet_watt = Number(document.querySelector('input').value);
  if (outllet_watt <= watt_number) {
    watt = outllet_watt;
  } else {
    watt = watt_number;
  }
  // 選択された電化製品のワット数から、合計電力を計算
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
    document.getElementById("safety").innerHTML = "あと <a class ='usedwatt'>" + can_use_watt + "</a> W 使用できます";

    // 計算結果登録用のテキストボックス等をinnerHTMLで表示
    let reg_maker_name = "メーカー&emsp;<input type='text' name='item_maker_name' value=" + ps_maker_name + " autocomplete='off'>";
    let reg_name = "商品名&emsp;&emsp;<input type='text' name='item_name' value=" + ps_name + " autocomplete='off'>";
    let reg_code = "商品コード<input type='text' name='item_code' value=" + ps_code + " autocomplete='off'>";
    let reg_ampere_number = "アンペア&emsp;<input type='number' step='0.1' name='ampere' value=" + sum_watt / 100 + ">";
    let reg_watt_number = "ワット&emsp;&emsp;<input type='number' name='watt' value=" + sum_watt + ">";
    let btn = "<button type='submit'>登録</button>";
    document.getElementById("maker_name").innerHTML = reg_maker_name;
    document.getElementById("name").innerHTML = reg_name;
    document.getElementById("code").innerHTML = reg_code;
    document.getElementById("ampere").innerHTML = reg_ampere_number;
    document.getElementById("watt").innerHTML = reg_watt_number;
    document.getElementById("btn_after_register").innerHTML = btn;
  } else {
    viewSafetyMessage.style.display = "none";
    viewDangerMessage.style.display = "block";
    document.getElementById("danger").innerHTML = "あと <a  style='color:red'>" + -can_use_watt + "</a>W 減らしてください";
  }
  document.getElementById("usedwatt").innerHTML = sum_watt;
}

function NotViewSafetyDanger() {
  viewSafetyMessage.style.display = "none";
  viewDangerMessage.style.display = "none";
}
