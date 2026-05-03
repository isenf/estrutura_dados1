public class Contact implements Comparable <Contact>{
    protected String CPF;
    protected String name;
    protected int age;
    protected String phone;
    protected String email;

    // construtores
    Contact(String cpf, String n, int a, String p, String e){
        this.CPF = cpf;
        this.name = n;
        this.age = a;
        this.phone = p;
        this.email = e;
    }

    Contact(){
        this.CPF = "";
        this.name = "";
        this.age = 0;
        this.phone = "";
        this.email = "";
    }

    /** retorna o CPF */
    public String getCPF(){
        return CPF;
    }

    /** retorna o nome */
    public String getName(){
        return name;
    }

    /** retorna a idade */
    public int getAge(){
        return age;
    }

    /** retorna o número de telefone */
    public String getPhone(){
        return phone;
    }

    /** retorna o email */
    public String getEmail(){
        return email;
    }

    /** modifica o valor do CPF */
    public void setCPF(String newCPF){
        CPF = newCPF;
    }

    /** modifica o valor do nome */
    public void setName(String newName){
        name = newName;
    }

    /** modifica o valor da idade */
    public void setAge(int newAge){
        age = newAge;
    }

    /** modifica o valor do telefone */
    public void setPhone(String newPhone){
        phone = newPhone;
    }

    /** modifica o valor do email */
    public void setEmail(String newEmail){
        email = newEmail;
    }

    /** retorna uma string com as informações */
    public String toString(){
        return "" + name + "\t" + CPF + "\t" + age + "\t" + phone + "\t" + email;
    }

    @Override
    public int compareTo(Contact other){
        return this.name.compareTo(other.name);
    }

    // testa a classe
    public static void main(String[] args){
        Contact c1 = new Contact("123.456.789-00", "Dante", 19, "98-76543210", "teste@exemple.com");
        
        System.out.println(c1);
    }

}