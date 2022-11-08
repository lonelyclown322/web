function cleanOtherOptions(id) {
    let divNumber = id[5];
    for (let propertyName in PrimeFaces.widgets) {
        if (PrimeFaces.widgets[propertyName] instanceof PrimeFaces.widget.SelectBooleanCheckbox) {
            if (PrimeFaces.widgets[propertyName] !== PrimeFaces.widgets['widget_form_' + id]) {
                if (divNumber === propertyName[17]) {
                    PrimeFaces.widgets[propertyName].uncheck();
                }
            }
        }
    }
}


addEventListener('load', (event) => {

    $('input[type="checkbox"]').on('change', function() {
        if (this.checked) {
            if (this.id[10] === '2') {
                R = (1 + parseInt(this.id[12])) / 2;
                // console.log(R);
                // console.log(this.id[12]);
                // console.log(this.id[12] + 1)
                // console.log((this.id[12] + 1) / 2)
                // console.log(this.id);
                // console.log(this);
                changeGraph(R);


            }
            // console.log($('input[type="checkbox"]').not(this));
            // console.log(this.id.substring(5, 13))
            cleanOtherOptions(this.id.substring(5, 13))
        }
    });
});