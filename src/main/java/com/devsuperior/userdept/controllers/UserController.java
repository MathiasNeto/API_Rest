package com.devsuperior.userdept.controllers;

import com.devsuperior.userdept.entities.User;
import com.devsuperior.userdept.repositories.UserRepository;
import org.hibernate.Remove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*Quando você anota um campo, construtor ou método com @Autowired,
o Spring (ou outro framework de injeção de dependência) irá procurar uma instância da classe de dependência e
fornecê-la automaticamente para o componente que contém a anotação @Autowired.
Essa anotação ajuda a evitar a necessidade de instanciar manualmente as dependências, permitindo que o Spring
gerencie a configuração das dependências e simplifique o código. É uma maneira conveniente de integrar componentes
de uma aplicação Spring, permitindo que as classes trabalhem juntas de forma coesa e organizada.
 */
@RestController //Configura essa classe para ser um end-point, um controlador Rest
@RequestMapping(value = "/users") //Caminho que esse controlador ira responder
public class UserController {


    @Autowired //é usada em Java para injetar automaticamente uma dependência em um componente
    private UserRepository repository;//Composicao de componentes
    //Pra nao ter que todas as vezes que for instanciar esse cara aqui, iremos usar injecao de dependencia
//

    @GetMapping //Quando vc faz uma requisicao web devo colocar qual é a operacao get, post, push, delete
    public List<User> findAll() { //Busca todos os banco de dados
        return repository.findAll();
    }

    //Quando vc faz uma requisicao web devo colocar qual é a operacao get, post, push,
    // delete
    @GetMapping(value = "/{id}")//Passa o caminho da busca por id
    public User findById(@PathVariable Long id) {  //Para que o id do getMapping seja igual o do parametro
        //Busca o usuario por id
        return repository.findById(id).get();
    }
    @PostMapping//Usando o Metodo post
    public void insert(@RequestBody User user) {  //Request body diz que sera passado um corpo para esse metodo na
        // hora inserir
        //retorna o usuario salvo
        repository.save(user);
        System.out.println("O usuario foi salvo com sucess");
    }


    @DeleteMapping(value = "{id}")//Nesse caso passou-se a operacao web post que é pra salvar
    public void remove(@PathVariable ("id") Long id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
        }
    }
}
