import Foundation

// 1.1
var num1:Float = 4;
var num2:Float = 5.0;
var sum = num1 + num2;
print("Wynik dodawania: \(num1) + \(num2) to \(sum)")

let constInt:Int = 1000;
//1.2 nie jestesmy w stanie zincrementować stałej zmiennej ->
//  deklarowanie zmiennych przez let jest mozliwa modyfikacja tylko podczas inicjalizacji
let incremented:Int = constInt+1;
print("incremented: \(incremented)")


// 1.3, 1.4. 1.5

//let str = readLine()
//print(str) // Optional ("7")


let integeredReaded:Int
if let readed = readLine() {
    if let num = Int(readed) {
        integeredReaded = num
        print("Converted")
    } else {
        print("Converting error")
    }
}

// 1.6
// Optional binding is the recommended way to unwrap an optional.
// Swift Optional Binding is used to find out whether an optional contains a value or not. 
// If it does then make that value available as a temporary constant or variable.

var desc: String? = "Optionals"

if let temp = desc{
  print(temp)
}
else{
 print("No description")
}

// end recommended way to unwrap optional


// Control Flow

// 2.1

print("Calculator started, press q to exit")
let exitCode:String = "q";

var exit:Int = 1
var index:Int = 0
repeat {

    let chosenOpt:String
    if let readed = readLine() {
        if let num:String = readed {
            print(num);    
            print("Converted")
        } else{
            print("Converting error")
        }
    }
    
    index+=1;
    if index > 5 {
        exit = 0;
    }
    
    //print("option \(chosenOpt)");
} while 1 == exit



let unusualMenagerie = "Koala 🐨, Snail 🐌, Penguin 🐧, Dromedary 🐪"
print("unusualMenagerie has \(unusualMenagerie.count) characters")
// Prints "unusualMenagerie has 40 characters


print(unusualMenagerie)


print("Calculator Finished!")


do {
    print("incremented: \(incremented)")
} catch let error as NSError  {
    print(error.description)
} catch {
    print("Unhandled ")
}