package org.fzb.rpcfx.demo.api;

/**
 * Order
 *
 * @author fengzhenbing
 */
public class Order {
    private Integer id;

    private String name;

    private Float amount;

    public Order(Integer id, String name, Float amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

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

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

}