function getON(items) {
  //JSONの処理
  const obj = JSON.parse(items);
  //電源タップのコンセント数を取ってくる
  let outllet_number = document.querySelector('select').value;
  var str = "";
  for (let i = 0; i < outllet_number; i++) {
    str += "<select id='itemselect" + i + "' name='itemselect" + i + "'>";
    for (let j = 0; j < obj.length; j++) {
      str += "<option value =" + obj[j].watt + ">" + obj[j].item_maker_name + " / " + obj[j].item_name + " / " + obj[j].item_code + "</option>";
    }
    str += "</select>";
  }
  document.getElementById('item').innerHTML = str;
}


function test() {
  let outllet_number = document.querySelector('select').value;
  let watt = document.querySelector('input').value;
  for (let i = 0; i < outllet_number; i++) {
    let item_data = document.getElementById('itemselect' + i).value;
    console.log(item_data.split(','));
  }
  console.log(watt);
}
