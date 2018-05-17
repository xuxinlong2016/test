//全选
function ca(obj){
  //alert(obj);
  var o = obj;

  if(!o)  return;
  if(o.length==null){
    o.checked=true;
    return;
  }
  for(i=0;i<o.length;i++){
    o[i].checked=true;
  }
}
//反选
function co(obj){
  var o = obj;

  if(!o)  return;
  if(o.length==null){
    if(o.checked){
       o.checked=false;
    }
    else if(!o.checked){
       o.checked=true;
    }
    return;
  }
  for(i=0;i<o.length;i++){
    if(o[i].checked==true)
       o[i].checked=false;
    else if(o[i].checked==false)
       o[i].checked=true;
  }
}

function convertCurrency(currencyDigits) {
    var MAXIMUM_NUMBER = 99999999999.99;
    var CN_ZERO = "零";
    var CN_ONE = "壹";
    var CN_TWO = "贰";
    var CN_THREE = "叁";
    var CN_FOUR = "肆";
    var CN_FIVE = "伍";
    var CN_SIX = "陆";
    var CN_SEVEN = "柒";
    var CN_EIGHT = "捌";
    var CN_NINE = "玖";
    var CN_TEN = "拾";
    var CN_HUNDRED = "佰";
    var CN_THOUSAND = "仟";
    var CN_TEN_THOUSAND = "万";
    var CN_HUNDRED_MILLION = "亿";
    var CN_SYMBOL = "";
    var CN_DOLLAR = "元";
    var CN_TEN_CENT = "角";
    var CN_CENT = "分";
    var CN_INTEGER = "整";

// Variables:
    var integral;    // Represent integral part of digit number.
    var decimal;    // Represent decimal part of digit number.
    var outputCharacters;     
    var parts;
    var digits, radices, bigRadices, decimals;
    var zeroCount;
    var i, p, d;
    var quotient, modulus; 
    currencyDigits = currencyDigits.toString();
    //if ((currencyDigits).match(/^((\d{1,3}(,\d{3})*(.((\d{3},)*\d{1,3}))?)|(\d+(.\d+)?))$/) == null) {
       // alert("抱歉,你填写的金额格式不正确!");
       // document.forms[0].elements("yfzbjdx").value="";
        //document.forms[0].elements("zyfzbj").value="";
        //return "";
    //}

// Normalize the format of input digits:
 

    currencyDigits = currencyDigits.replace(/,/g, "");    // Remove comma delimiters.
    currencyDigits = currencyDigits.replace(/^0+/, "");    // Trim zeros at the beginning.
    // Assert the number is not greater than the maximum number.

    currencyDigits=currencyDigits*1+0.005;
 	currencyDigits = currencyDigits+"";
 	currencyDigits=currencyDigits.substring(0, currencyDigits.indexOf(".")+3); 
    parts = currencyDigits.split(".");
    if (parts.length > 1) {
        integral = parts[0];
        decimal = parts[1];
        decimal = decimal.substr(0, 2);
    }
    else {
        integral = parts[0];
        decimal = "";
    }
    digits = new Array(CN_ZERO, CN_ONE, CN_TWO, CN_THREE, CN_FOUR, CN_FIVE, CN_SIX, CN_SEVEN, CN_EIGHT, CN_NINE);
    radices = new Array("", CN_TEN, CN_HUNDRED, CN_THOUSAND);
    bigRadices = new Array("", CN_TEN_THOUSAND, CN_HUNDRED_MILLION);
    decimals = new Array(CN_TEN_CENT, CN_CENT);
    outputCharacters = "";
    if (Number(integral) > 0) {
        zeroCount = 0;
        for (i = 0; i < integral.length; i++) {
            p = integral.length - i - 1;
            d = integral.substr(i, 1);
            quotient = p / 4;
            modulus = p % 4;
            if (d == "0") {
                zeroCount++;
            }
            else {
                if (zeroCount > 0)
                {
                    outputCharacters += digits[0];
                }
                zeroCount = 0;
                outputCharacters += digits[Number(d)] + radices[modulus];
            }
            if (modulus == 0 && zeroCount < 4) {
                outputCharacters += bigRadices[quotient];
            }
        }
        outputCharacters += CN_DOLLAR;
    }
    // Process decimal part if there is:
    if (decimal != "") {
        for (i = 0; i < decimal.length; i++) {
            d = decimal.substr(i, 1);
            if (d != "0") {
                outputCharacters += digits[Number(d)] + decimals[i];
            }
        }
    }
    // Confirm and return the final output string:
    if (outputCharacters == "") {
        outputCharacters = CN_ZERO + CN_DOLLAR;
    }
    if (decimal == "") {
        outputCharacters += CN_INTEGER;
    } 
    outputCharacters = CN_SYMBOL + outputCharacters;
    return outputCharacters;
}

//格式化金额
//return 金额格式的字符串,如'1,234,567.45'      
function formatCurrency(num) {
    num = num.toString().replace(/\$|\,/g,'');
    if(isNaN(num))
    num = "0";
    sign = (num == (num = Math.abs(num)));
    num = Math.floor(num*100+0.50000000001);
    cents = num%100;
    num = Math.floor(num/100).toString();
    if(cents<10)
    cents = "0" + cents;
    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
    num = num.substring(0,num.length-(4*i+3))+','+
    num.substring(num.length-(4*i+3));
    return (((sign)?'':'-') + num + '.' + cents);
}
       