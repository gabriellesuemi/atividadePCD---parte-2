# Atividade Projeto Orientado a Objetos - Inversão de Controle

 ## Aluna: Gabrielle Suemi Iquejiri Alencar   RA: 176325

 Atividade de projeto orientado a objetos, de projeto de coleta de dados (PCD). Na atividade, possui os observadores, que são as cidades BSB, RJ, SJC, SP e POA, e os sujeitos, que são sensores localizados no estado da Amazônia. Os observadores recebem as informações das condições de temperatura, ph e umidade do ar dos sensores que estão interessados e imprimem essas informações.

 O projeto utiliza o padrão Observer com uma adaptação utilizando Inversão de Controle (IoC) e Callback.

 Em vez de o sensor depender diretamente de objetos observadores, ele trabalha com callbacks registrados pelas cidades. Dessa forma, o controle da resposta foi invertido: o sensor apenas executa as funções cadastradas, sem precisar conhecer diretamente quem irá receber os dados.

 Isso reduz o acoplamento entre as classes e torna o sistema mais flexível e de fácil manutenção.


