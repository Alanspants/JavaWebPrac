window.onload = main;

function main() {
    // 1. 为数据行所有checkbox绑定onclick关联func2
    var domArray = document.getElementsByTagName("input");
    for (var i = 1; i < domArray.length; i++) {
        domArray[i].onclick = func2;
    }
    // 2. 为所有数据行绑定onmouseover关联func3
    domArray = document.getElementsByTagName("tr");
    for (var i = 1; i < domArray.length; i++) {
        domArray[i].onmouseover = func3;
        domArray[i].onmouseout = func4;
    }
}

// 全选 全不选，  通过标题行选中状态控制所有数据行选中状态
function func1() {
    // 1. 获得页面中所有checkbox关联的dom对象
    var domArray = document.getElementsByTagName("input");
    // 2. 获得标题行checkbox标签的check属性值
    var flag = domArray[0].checked;
    // 3. 对数据行中所有checkbox的checked属性值进行统一赋值
    for (var i = 1; i < domArray.length; i++) {
        var domObj = domArray[i];
        domObj.checked = flag;
    }
}

// 全选 全不选，  通过数据行选中状态控制标题行选中状态
function func2() {
    // 1. 获得所有行（数据行+标题行）checkbox对应dom
    var domArray = document.getElementsByTagName("input");
    // 2. 数据行被选中的checkbox个数
    var checkedNum = 0;
    for (var i = 1; i < domArray.length; i++) {
        var dom = domArray[i];
        if (dom.checked == true) checkedNum++;
    }
    // 3. 判断
    if (checkedNum == domArray.length - 1) {
        domArray[0].checked = true;
    } else {
        domArray[0].checked = false;
    }
}

// 鼠标悬停 [数据行] 背景颜色设置红色
function func3() {
    this.style.backgroundColor = "red";
}

// 鼠标离开 [数据行] 背景颜色设置白色
function func4() {
    this.style.backgroundColor = "white";
}
