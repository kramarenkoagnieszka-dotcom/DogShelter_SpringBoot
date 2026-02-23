package org.mydomain.shelterspringboot.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dogs")
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
    private String breed;
    private boolean isAdopted;

    @OneToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private DogProfile profile;

    @OneToMany(mappedBy = "dog", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Expense> expenseList;

    public Dog(String name, int age, String breed, DogProfile profile) {
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.profile = profile;
        this.isAdopted = false;
        this.expenseList = new ArrayList<>();
    }

    protected Dog() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }

    public boolean isAdopted() { return isAdopted; }
    public void setAdopted(boolean adopted) { isAdopted = adopted; }

    public DogProfile getProfile() { return profile; }
    public void setProfile(DogProfile profile) { this.profile = profile; }

    public List<Expense> getExpenses() { return expenseList; }
    public void setExpenses(List<Expense> expenseList) { this.expenseList = expenseList; }

    public void addExpense(Expense expense) {
        if (this.expenseList == null) {
            this.expenseList = new ArrayList<>();
        }
        this.expenseList.add(expense);
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", breed='" + breed + '\'' +
                ", isAdopted=" + isAdopted +
                '}';
    }
}