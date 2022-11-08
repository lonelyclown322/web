addEventListener('load', (event) => {

    document.getElementById("form:text_input").oninput = onlyDigits;

});

function onlyDigits() {
    let separator = ".";
    let replaced = new RegExp('[^\\d\\'+separator+'\\-]', "g");
    let regex = new RegExp('\\'+separator, "g");
    this.value = this.value.replace(replaced, "");

    let minValue = parseFloat(-3);
    let maxValue = parseFloat(5);
    let val = parseFloat(separator === "." ? this.value : this.value.replace(new RegExp(separator, "g"), "."));

    if (minValue <= maxValue) {
        if (this.value[0] === "-") {
            if (this.value.length > 8) {
                this.value = this.value.substr(0, 8);
            }
        } else {
            if (this.value.length > 7) {
                this.value = this.value.substr(0, 7);
            }
        }

        if (this.value[0] === separator) {
            this.value = "0" + this.value;
        }

        if (minValue < 0 && maxValue < 0) {
            if (this.value[0] !== "-")
                this.value = "-" + this.value[0];
        } else if (minValue >= 0 && maxValue >= 0) {
            if (this.value[0] === "-")
                this.value = this.value.substr(0, 0);
        }

        if (val < minValue || val > maxValue) {
            this.value = this.value.substr(0, 0);
        }
        if (this.value.match(regex))
            if (this.value.match(regex).length > 1) {
                this.value = this.value.substr(0, 0);
            }

        if (this.value.match(/\-/g))
            if (this.value.match(/\-/g).length > 1) {
                this.value = this.value.substr(0, 0);
            }

        if (isNaN(this.value) && this.value.length > 1) {
            this.value = this.value.substr(0, 0);
        }

    }
}
