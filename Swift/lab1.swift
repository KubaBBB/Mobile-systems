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

func Pow (base:Int, power:UInt) -> Int {
  var answer : Int = 1
  for _ in 0...power { answer *= base }
  return answer
};

let powTest = Pow(base:2, power:3);
print("Power test, (2,3): \(powTest)")


func Factorial(_ n: Int) -> Double {
  return (1...n).map(Double.init).reduce(1.0, *)
}

let factorialTest = Factorial(4);
print("Factorial test 4!: \(factorialTest)")

func Multiply (num1:Double, num2:Double) -> Double {
    return num1 * num2;
}

let MultiplyTest = Multiply(num1:4, num2:3)
print("Multiply test 3,4: \(MultiplyTest)")

func Divide(num1:Double, num2:Double) -> Double {
    return num1 / num2;
}

let divideTest = Divide(num1:Double(12), num2:Double(3))
print("Divide test 4!: \(divideTest)")

func Add (num1:Double, num2:Double) -> Double {
    return num1 + num2;
}

let AddTest = Add(num1:4, num2:3)
print("Add test 3,4: \(AddTest)")


func Substract (num1:Double, num2:Double) -> Double {
    return num1 - num2;
}

let SubstractTest = Substract(num1:4, num2:3)
print("Substract test 3,4: \(SubstractTest)")

var exit:Int = 1
var index:Int = 0

var n1:Double = 0, n2:Double = 0;
repeat {
    
    if let typed = readLine() {
        if let num = Double(typed) {
        n1 = num
        } else {
        print("Not a number")
        continue
        }
    }
    
    if let typed = readLine() {
        if let num = Double(typed) {
        n2 = num
    } else {
        print("Not a number")
        continue
        }
    }
    
    //print("Selected numbers -> num1: \(n1), num2: \(n2)")
    //guard let g1 = n1, let g2 = n2 else { continue }

    let chosenOpt:String
    if let readed = readLine() {
        if let num:String = readed {
            print(num);    
            print("Converted")
            
            switch num {
                case "+":
                    print("Calculator addition: \(Add(num1:n1, num2:n2))");
                    break;
                case "-":
                    print("Calculator substraction: \(Substract(num1:n1, num2:n2))");
                    break;
                case "*":
                    print("Calculator multiply: \(Multiply(num1:n1, num2:n2))");
                    break;
                case "/":
                    print("Calculator Divide: \(Divide(num1:n1, num2:n2))");
                    break;
                case "^":
                    print("Calculator Pow: \(Pow(base:Int(n1), power:UInt(n2)))");
                    break;
                case "!":
                    print("Calculator Factorial: \(Factorial(Int(n1)))");
                    break;
                case "q":
                    exit = 0
                    break;
                default:
                    print("OP not supported")
                }
                    print("Starting next calculation")
                    continue
        } else {
            print("Converting error")
        }
    }
    
    index+=1;
    if index > 5 {
        exit = 0;
    }
} while 1 == exit

let unusualMenagerie = "Koala 🐨, Snail 🐌, Penguin 🐧, Dromedary 🐪"
print("unusualMenagerie has \(unusualMenagerie.count) characters")
// Prints "unusualMenagerie has 40 characters

print(unusualMenagerie)

print("Calculator Finished!")


// 2.2 Fibonacci

func Fibonacci(n: Int) -> Int {
    var n1 = 0;
    var n2 = 1
    for _ in 1 ..< n {
    let temp = n1 + n2
        n1 = n2
        n2 = temp
    }
    print("result = \(n2)")
    return n2
}

let FibTest:Int = Fibonacci(n:4)
print("Fibonacci 4: \(FibTest)")


// 2.3

func Pyramid(n: Int) -> String{
    var j: Int = 0
    var pattern = String()
    for i in 1...n {
       for _ in 1...n+1 - i {
           pattern += " "
       }
       for j in 1...i {
           pattern += "a"
       }

       j = i - 1
       while j >= 1 {
           pattern += "a"
           j -= 1
       }
        pattern += "\n"
   }
   //print(pattern)
   return pattern
}

var pyramid = Pyramid(n:4);

// 2.4 Reverse string

let str = "Hello my friend"
let RevRes = String(str.reversed()) // This will give you cba
//print("Reversed: \(RevRes)")


// 3.1 Tables unique names

let names = ["Krzysiek", "Miroslaw", "Jakub", "Jakub"]
print(Set(names))

class Animal : CustomStringConvertible {
    var name: String
    var age: Int
    var species: String
    
    init(name: String, age: Int, species: String) {
        self.name = name
        self.age = age
        self.species = species
    }
    
    func move() {
        print("\(name): I'm moving.")
    }
    
    var description: String {
    return "Hello. Im: \(species). My name is:\(name). I am:\(age) years old."
  }
}

var a = Animal(name: "Puffy", age: 7, species: "dog")
print(a)
a.move()

class Fish :Animal {
    override func move(){
        print("\(name): Im swimming")
    }
}

class Dog :Animal {
    override func move(){
        print("\(name): Im running")
    }
}

var f = Fish(name: "Rick", age:2, species:"Fish")
print(f)
f.move()
var d = Dog(name: "Morty", age:3, species:"Dog")
print(d)
d.move()



var fAnimal:Animal = Fish(name: "Sully", age:21, species:"fAnimal")
fAnimal.move()


func PrintUpperIdx(nameList:[String]) -> Void {
    var idx = 0
    for name in nameList {
        var isUpper = true
        var arr = Array(name)
        for i in 0..<arr.count {
            if arr[i].isLowercase{
                isUpper = false
            }
        }   
        if isUpper {
            print("name:\(name), at index: \(idx)")
        }
        idx += 1
    }
}
let names2 = ["Monika","Monika", "MONIKA", "Andrzej", "Monika", "STEFAN", "Andrzej", "JUREK", "KAMIL"]
PrintUpperIdx(nameList:names2)


func occurences(arr:[String]) -> Void{
    var strDict: [String: Int] = [:]
    for item in arr{
    let keyExists = strDict[item] != nil;
        if keyExists {
            strDict[item]! += 1
        }
        else
        {
            strDict[item] = 1

        }
    }
    
    print(strDict)
}

let names3 = ["Monika","Monika", "MONIKA", "Andrzej", "Monika", "STEFAN", "Andrzej", "JUREK", "KAMIL"]
occurences(arr:names3)


enum Choices {
    case rock
    case paper
    case scissors
}

enum Result{
    case win
    case lost
    case draw
}

func game(p1:Choices, p2:Choices) -> Result{
    switch p1{
        case .rock:
            switch p2 {
                    case .rock:
                        return Result.draw
                    case .paper:
                        return Result.lost
                    case .scissors:
                        return Result.win
                        
                }
        case .paper:
            switch p2 {
                    case .rock:
                        return Result.win
                    case .paper:
                        return Result.draw
                    case .scissors:
                        return Result.lost
                }            
        case .scissors:
            switch p2 {
                    case .rock:
                        return Result.lost
                    case .paper:
                        return Result.win
                    case .scissors:
                        return Result.draw
                }
            
    }
    return Result.draw
}


let p1:Choices = Choices.rock
let p2:Choices = Choices.rock
let resGame = game(p1:p1, p2:p2)
print("(\(p1), \(p2), \(resGame))")


func factorial(of num: Int) -> Int {
    if num == 1 {
        return 1
    } else {
        return num * factorial(of:num - 1)
    }
}

let x = 4
let result = factorial(of: x)
print("The factorial of \(x) is \(result)")


func fibs(n: Int) -> Int {
    
    if n == 0 {
        return 0
    } else if n == 1{
        return 1
    }
    
    return fibs(n - 1) + fibs(n - 2)
}
