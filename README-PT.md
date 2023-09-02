# Scrum Board

Em Ciência da Computação, especificamente nas áreas de desenvolvimento e engenharia de software, é comum o desenvolvimento de projetos usando metodologias ágeis como o Scrum. 

Projetos baseados em Scrum fazem uso de um Scrum Board (imagem abaixo), que consiste em um quadro que reúne informações sobre as User Stories e Taks relacionadas ao projeto (como status, assignments, progresso, etc.) para auxiliar a gerência de desenvolvimento e a mitigação de riscos. 

![Scrum Board](/media/image.png)

O sistema Scrum Board tem o objetivo de prover um backend padrão para o desenvolvimento de scrum boards, de forma a facilitar o desenvolvimento de projetos baseados em Scrum. 


### User Stories

> US 1 - Eu, como Usuário, gostaria de ter acesso no sistema a um CRUD de usuário, a partir de informações de nome completo, username e email. 
<br>    OBS.: Apenas o próprio usuário pode realizar operações no seu cadastro
        
> US 2 - Eu, como Scrum Master, gostaria de ter acesso no sistema a um CRUD de projetos, a partir de informações de nome do projeto, descrição e instituição parceira.
<br>    OBS.: O Scrum Master fica automaticamente associado ao projeto criado no papel de scrum master
        
> US 3 - Eu, como Scrum Master, gostaria de ter acesso a papéis que podem ser assumidos por usuários associados a projetos.
<br>    OBS.:  Os papéis disponibilizados devem ser: product owner, pesquisador, desenvolvedor e estagiário
        
> US 4 - Eu, como Scrum Master, gostaria de associar um usuário cadastrado no sistema a um determinado projeto, especificando o papel assumido pelo usuário no projeto
<br>    OBS.:  Só é possível ao Scrum Master associar usuários a um projeto do qual ele faz parte
        
> US 5 -  Eu, como Scrum Master, gostaria de ter acesso a estágios de desenvolvimento nos projetos criados no sistema. Cada User Store do projeto estará associada a um estágio de desenvolvimento. 
<br>    OBS.: Os estágios disponibilizados devem ser: TODO, Work in Progress, To Verify e Done
        
> US 6 - Eu, como > USuário, gostaria de ter acesso no sistema a um CRUD de User Stories, a partir de informações de título e descrição da user story.
<br>    OBS.: Os usuários só podem realizar operações em user stories de projetos dos quais fazem parte. 
<br>    OBS.: Toda user story criada pertence ao estágio de desenvolvimento TODO
        
> US 7 - Eu, como Usuário, gostaria de poder me atribuir a uma determinada user story como um dos possíveis responsáveis pelo seu desenvolvimento.
<br>    OBS.: Mais de um usuário pode se associar a uma mesma user story
<br>    OBS.: Apenas usuários com os papéis de pesquisador, desenvolvedor ou estagiário podem se associar a uma user story
       
> US8 - Eu, como Scrum Master, gostaria de poder atribuir uma determinada user story a um usuário associado ao projeto.
<br>    OBS.: Apenas usuários com os papéis de pesquisador, desenvolvedor ou estagiário podem ser atribuídos a uma user story
      
> US 9 - Eu, como Usuário, gostaria de ter acesso no sistema a um CRUD de Tasks, a partir de informações de título, descrição da task e user story associada.
<br>    OBS.: Apenas o Scrum Master ou usuários atribuídos à user story associada a uma task podem realizar operações na task em questão
<br>    OBS.: Toda task criada possui o status de não realizada
      
> US10 - Eu, como Usuário, gostaria de marcar uma Task como realizada
<br>    OBS.: Apenas o Scrum Master ou usuários atribuídos à user story associada a uma task podem marcar a task como realizada
<br>    OBS.: Apenas tasks com status de não realizada podem ser marcadas como realizadas
   
> US11 - Eu, como Usuário, gostaria de poder mover uma determinada user story no estágio Work in Progress para o estágio To Verify.
<br>    OBS.: Apenas user stories no estágio Work in Progess podem ser movidas para To Verify
<br>    OBS.: Apenas o Scrum Master ou usuários atribuídos à user story podem movê-la para o estágio To Verify

> US12 - Eu, como Scrum Master, gostaria de poder mover uma determinada user story no estágio To Verify para o estágio Done.
<br>    OBS.: Apenas user stories no estágio To Verify podem ser movidas para Done
<br>    OBS.: Apenas o Scrum Master pode mudar o estágio de user stories em To Verify para Done

> US13 - Eu, como responsável do Scrum Board, gostaria que o sistema realizasse mudanças automáticas de estágio de desenvolvimento de user stories cadastradas. As mudanças devem ser realizadas após os seguintes eventos: 
<br>    OBS.: TODO para Work in Progress: sempre que uma user story é atribuída a um usuário associado ao projeto
<br>    OBS.: Work in Progress para To Verify: sempre que todas as tasks de uma user story forem marcadas como realizadas

> US14 - Eu, como Usuário, gostaria de poder me interessar em uma User Story em desenvolvimento, para ser notificado a cada atualização da user story 
<br>    OBS.: A notificação deve ser representada como uma mensagem no terminal da aplicação, indicando quem está recebendo a notificação e o motivo desta
<br>    OBS.: Atualizações de interesse em uma user story são: mudança de descrição e  mudança de estágio

> US15 - Eu, como Scrum Master, gostaria de receber notificações a cada atualização de User Story realizada no sistema
<br>    OBS.: A notificação deve ser representada como uma mensagem no terminal da aplicação, indicando quem está recebendo a notificação e o motivo desta
<br>    OBS.: Atualizações de interesse em uma user story são: mudança de descrição,  mudança de estágio e task marcada como realizada

> US16 - Eu, como Product Owner, gostaria de receber notificações a cada user story finalizada no sistema (marcada como Done)
<br>    OBS.: A notificação deve ser representada como uma mensagem no terminal da aplicação, indicando quem está recebendo a notificação e o motivo desta

> US17 - Eu, como Usuário, gostaria de ter acesso a relatórios descritivos no status das user stories desenvolvidas as quais estou atribuído. Deve ser descrito no relatório: 
<br>    OBS.: O percentual e o número total de user stories que estão atribuídas ao usuário
<br>    OBS.: O percentual e o número de user stories atribuídas ao usuário em cada estágio de desenvolvimento

> US18 - Eu, como Product Owner, gostaria de ter acesso a relatórios descritivos no status das user stories desenvolvidas em todo o projeto.  Deve ser descrito no relatório: 
<br>    OBS.: O percentual e o número de user stories em cada estágio de desenvolvimento

> US19 - Eu, como Scrum Master, gostaria de ter acesso a relatórios descritivos no status das user stories desenvolvidas em todo o projeto, com detalhamento da atuação dos usuários.  Deve ser descrito no relatório: 
<br>    OBS.: O percentual e o número total de user stories que estão atribuídas a cada usuário
<br>    OBS.: O percentual e o número de user stories atribuídas a cada usuário em cada estágio de desenvolvimento
O percentual e o número de user stories em cada estágio de desenvolvimento

### Desenvolvedores:

- @AlefAdonis
- @huggoparcelly
- @danielMaciel710
- @marianezei
- @johanssonlucena