function getON(items) {
  // JSONの処理
  const obj = JSON.parse(items);
  // 電源タップのコンセント数を取ってくる
  let outllet_number = document.querySelector('select').value;
  var str = "";
  for (let i = 0; i < outllet_number; i++) {
    str += "<select id='itemselect" + i + "' name='itemselect" + i + "'>";
    for (let j = 0; j < obj.length; j++) {
      str += "<option value =" + obj[j].watt + ">" + obj[j].item_maker_name + " / " + obj[j].item_name + " / " + obj[j].item_code + "</option>";
    }
    str += "<option value = 0>空</option>";
    str += "</select>";
  }
  document.getElementById('item').innerHTML = str;
}


function calculation() {
  document.getElementById("aftercalculation").style.display = "block";
  let outllet_number = document.querySelector('select').value;
  let sum_watt = 0;
  const watt = document.querySelector('input').value;
  for (let i = 0; i < outllet_number; i++) {
    let item_data = Number(document.getElementById('itemselect' + i).value);
    sum_watt += item_data;
  }
  let can_use_watt = watt - sum_watt;

  // 安全or危険の表示
  const viewSafetyMessage = document.getElementById("safety");
  const viewDangerMessage = document.getElementById("danger");
  if (can_use_watt >= 0) {
    viewSafetyMessage.style.display = "block";
    viewDangerMessage.style.display = "none";
    document.getElementById("safety").innerHTML = "あと " + can_use_watt + "W 使用できます";
  } else {
    viewSafetyMessage.style.display = "none";
    viewDangerMessage.style.display = "block";
    document.getElementById("danger").innerHTML = "あと <a style='color:red'>" + -can_use_watt + "</a>W 減らしてください";
  }
  document.getElementById("usedwatt").innerHTML = sum_watt;
}
