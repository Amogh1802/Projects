document.addEventListener("keydown", function(event) {
    
    let key = event.key.toUpperCase();

    
    if (["W", "A", "S", "D", "Q", "E","Z","X"].includes(key)) {
        console.log(`${key} key is pressed`);
    }
    if (event.code = "Space"){
        console.log("STOP key is pressed");
    }
});
