package com.example.lab3;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class RemoteCommandView {

    @ManagedProperty(value = "#{dataTable}")
    private DataTable dataTable;

    public DataTable getDataTable() {
        return dataTable;
    }

    public void setDataTable(DataTable dataTable) {
        this.dataTable = dataTable;
    }

    public void execute() {
        System.out.println(dataTable.getHits().size());
        String xValue = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("xValue");
        String yValue = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("yValue");
        String rValue = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("rValue");
        if (xValue != null && yValue != null && rValue != null) {
            Hit hit = new Hit(Double.parseDouble(xValue),
                    Double.parseDouble(yValue),
                    Double.parseDouble(rValue),
                    dataTable.isHit(Double.parseDouble(xValue),
                            Double.parseDouble(yValue),
                            Double.parseDouble(rValue)));
            dataTable.save(hit);

        }
        System.out.println(Double.parseDouble(xValue));
        System.out.println(Double.parseDouble(yValue));

    }
}
