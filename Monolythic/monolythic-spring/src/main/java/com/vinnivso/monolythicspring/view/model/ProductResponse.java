package com.vinnivso.monolythicspring.view.model;

public class ProductResponse {
  // #region Attributes
  private Integer id;

  private String name;

  private Integer quantity;

  private Double value;

  private String observation;
  // #endregion Attributes

  // #region Getters and Setters
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public String getObservation() {
    return observation;
  }

  public void setObservation(String observation) {
    this.observation = observation;
  }
  // #endregion Getters and Setters

}
