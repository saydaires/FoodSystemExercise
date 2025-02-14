
# Arquiteturas de Composição e de Herança

### Introdução
A arquitetura de composição é um dos **princípios de design de projetos** feitos em uma linguagem cujo  paradigma é o da POO (Programação Orientada à Objetos).

### Conceitos técnicos
A arquitetura de composição é utilizada para substituir a hierarquia de heranças entre classes, quando uma classe depende de outras classes, mas o relacionamento entre elas consiste em **TEM UM** ao invés de **É UM**.

Analisando o último código estudado em sala de aula que aborda a Arquitetura de Composição, peguemos como exemplo um objeto cavalo (classe `Cavalo`). A classe `Cavalo` à qual esse objeto referencia, é uma extensão (herança) da classe `Animal`, pois todo cavalo **É UM** animal. No entanto, um cavalo possui algumas peculiaridades que diferem de outros animais, e para separar essas peculiaridades, utilizamos a **Arquitetura de Composição**. Analise o escopo da classe `Cavalo`:

```java
// CLASSE CAVALO
import comportamentos.Andar;  
import comportamentos.Falar;  
import comportamentos.Respirar;  
  
public class Cavalo extends Animal {  
  
    private Falar falar;  
    private Respirar respirar;  
    private Andar andar;  
  
    public Cavalo(String nome, int idade, String categoria) {  
        super(nome, idade, categoria);  
  
        falar = new Falar();  
        respirar = new Respirar();  
        andar = new Andar();  
    }  
  
    public void falar() {  
        this.falar.falar("relinxar");  
    }  
}
```

Observe que, a classe `Cavalo` herda da classe `Animal` como já dito anteriormente, no entanto, os **ATRIBUTOS / VARIÁVEIS DE INSTÂNCIA** dessa classe **são instâncias das classes `Falar`, `Respirar` e `Andar`.** 

Como todo `Cavalo` **É UM** `Animal`, aplicamos a herança entre essas classes (`Cavalo` é uma classe filha da superclasse `Animal`). **PERCEBA** que, quando possuímos a relação **É UM** entre duas classes (todo cavalo é um animal), utilizamos a arquitetura de Herança entre essas duas classes.

Dentro do **pacote de classes** *comportamentos*, existem as classes `Andar`, `Falar`, `Nadar`, `Respirar` e `Voar`. Obviamente, nem todos os objetos `Animal` irão possuir todos esses atributos. Por exemplo, um objeto `Passaro` pode voar, mas um objeto `Cavalo` **não pode voar**.

Dessa forma, podemos dizer que as classes do pacote *comportamentos* possuem um relacionamento **TEM UM** com as classes que estendem a superclasse `Animal`. Por exemplo, um objeto `Cavalo` **TEM UM** comportamento de `Andar`, `Respirar`, `Falar`, mas não possui comportamentos de `Voar` e  `Nadar`. 

Do exposto acima, quando duas classes possui um relacionamento **TEM UM** (uma classe TEM UMA outra classe), utilizamos a **Arquitetura de Composição**. A implementação dessa arquitetura ocorre quando, por exemplo, **instanciamos objetos das classes `Andar`, `Falar` e `Respirar` como sendo ATRIBUTOS da classe `Cavalo`**:
```java
public class Cavalo extends Animal {  
    private Falar falar;  
    private Respirar respirar;  
    private Andar andar;
    .
    .
    .
}      
```
A inicialização desses atributos instanciados ocorre no construtor da classe principal (`Cavalo`):
```java
public Cavalo(String nome, int idade, String categoria) {  
    super(nome, idade, categoria);  
  
    falar = new Falar();  // inicializando os Atribtos / instâncias
    respirar = new Respirar();  
    andar = new Andar();  
    } 
```


### Conclusão
Do que foi exposto no tópico anterior **Conceitos Técnicos**, utilizamos a *Herança entre classes* quando as classes **possuem relacionamento É UM** (um `Cavalo` é um `Animal`, uma `PessoaJuridica` é uma `Pessoa`), enquanto que utilizamos a *Composição de classes* quando uma classe **TEM UMA** outra classe como atributo / característica (as classes `Andar`, `Respirar`, `Falar` compõe a classe `Cavalo`).

---

# Injeção de Dependências
### Introdução
Injeção de Dependências é uma técnica de construção de software aplicando o princípio da **Inversão de Controle**, que no exemplo do último projeto feito em sala de aula, consiste em retirar a responsabilidade das subclasses de `Animal` de terem que gerenciar a instanciação das classes do pacote *comportamentos* no escopo delas. Essa instanciação é feita externamente, e passado para essas as classes `Animal` via-construtor ou métodos.

### Conceitos técnicos
Vejamos o código abaixo que **implementa o modelo de Composição de classes**, abordado no super-tópico (*Composição e Herança*) anterior:
```java
import comportamentos.Andar;  
import comportamentos.Falar;  
import comportamentos.Respirar;  
  
public class Cachorro extends Animal {  
    private Falar falar;  
    private Respirar respirar;  
    private Andar andar;  
  
    public Cachorro(String nome, int idade, String categoria) {  
        super(nome, idade, categoria);  
  
        falar = new Falar();  
        respirar = new Respirar();  
        andar = new Andar();  
    }  
  
    public void falar() {  
        this.falar.falar("au au");  
    }  
  
    public void respirar() {  
        this.respirar.respirar(10);  
    }  
  
    public void andar() {  
        this.andar.andar(10);  
    }  
}
```

Existem duas formas de aplicarmos a técnica de Injeção de Dependências nessa classe ***na arquitetura ATUAL do projeto*** (o pacote *comportamentos* possui classes, **e não interfaces**), às quais serão abordadas à seguir: 

#### 1. Via-construtor
Refazemos o código anterior aplicando a injeção de dependências através do construtor da classe `Cachorro`:
```java
import comportamentos.Andar;  
import comportamentos.Falar;  
import comportamentos.Respirar;  
  
public class Cachorro extends Animal {  
    private Falar falar;  
    private Respirar respirar;  
    private Andar andar;  
  
    public Cachorro(String nome, int idade, String categoria, 
    Falar falar, Respirar respirar, Andar andar) {  
        super(nome, idade, categoria);  
        this.falar = falar; this.respirar = respirar; this.andar = andar;  
    }
    .
    .
    . 
}
```
Observe que no código acima (que refatora a classe `Cachorro`), que as instâncias `Falar`, `Respirar`, `Andar` **deixaram de ser criadas no escopo da classe** `Cachorro`. Agora, elas são criadas externamente (no método que constrói um objeto `Cachorro`), **e passadas via-construtor da classe `Cachorro`.** Assim, a classe em questão não precisa mais se preocupar em construir as instâncias do pacote *comportamentos*, uma vez que essa classe receberá as instâncias já criadas via-construtor.

#### 2. Via-métodos
Nesse caso, estaríamos utilizando métodos **setters** para inicializar as instâncias das classes `Falar`, `Respirar` e  `Andar`:
```java
import comportamentos.Andar;  
import comportamentos.Falar;  
import comportamentos.Respirar;  
  
public class Cachorro extends Animal {  
    private Falar falar;  
    private Respirar respirar;  
    private Andar andar;  
  
    public Cachorro(String nome, int idade, String categoria) {  
        super(nome, idade, categoria);  
    }  
    public void setAndar(Andar andar) {  
        this.andar = andar;  
    }  
    public void setFalar(Falar falar) {  
        this.falar = falar;  
    }  
    public void setRespirar(Respirar respirar) {  
        this.respirar = respirar;  
    }  
    .
    .
    . 
}
```
Os métodos *setters* `setAndar()`, `setFalar()` e `setRespirar()` recebem como argumento os respectivos objetos das classes `Andar`, `Falar` e `Respirar`, e então inicializa as variáveis de instância da classe `Cachorro` que referenciam as três classes comportamentais citadas anteriormente.

### Conclusão
A técnica de **Injeção de Dependências** consiste em retirar das subclasses da superclasse `Animal`, a responsabilidade de construir as instâncias do pacote *comportamentos* dentro de seus próprios escopos. Esses objetos são construídos externamente, e passados para essas subclasses (`Cachorro`, `Cavalos`, etc.) através de métodos (**procedimentos void** ou **construtor**).