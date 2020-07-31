function aa() {
    let age = 16;
    return function bb() {
        return age;
    }
}

let userAge = aa()();
console.log(userAge);
userAge = null;/*释放闭包*/