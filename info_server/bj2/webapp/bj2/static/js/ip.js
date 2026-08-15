function ipToNumber(ip) {    
    var numbers = ip.split(".");    
    return parseInt(numbers[0])*256*256*256 +     
    parseInt(numbers[1])*256*256 +     
    parseInt(numbers[2])*256 +     
    parseInt(numbers[3]);    
}    
  
function numberToIp(number) {    
    return (Math.floor(number/(256*256*256))) + "." +     
    (Math.floor(number%(256*256*256)/(256*256))) + "." +     
    (Math.floor(number%(256*256)/256)) + "." +     
    (Math.floor(number%256));    
} 