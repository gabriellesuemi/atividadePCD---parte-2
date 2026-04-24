/*
*Nos testes, a alteração foi trocar adicionarObservador() e removerObservador()
por adicionarCallback() e removerCallback(), pois a classe Cidade não implementa
mais Observador. Agora, cada cidade registra seu método receberDados() como callback
usando cidade::receberDados, e esse callback é armazenado em uma variável para também
poder ser removido depois.

A vantagem disso é que o SensorAmazonia fica menos dependente das cidades,
reduzindo o acoplamento entre as classes. Assim, o código fica mais flexível,
mais fácil de manter e permite adicionar novos receptores sem alterar a lógica
do sensor.
*/

public class Testes {

    public static void main(String[] args) {

        SensorAmazonia sensor1 = new SensorAmazonia("Sensor AM-1");
        SensorAmazonia sensor2 = new SensorAmazonia("Sensor AM-2");

        Cidade bsb = new Cidade("BSB");
        Cidade rj = new Cidade("RJ");
        Cidade sjc = new Cidade("SJC");
        Cidade sp = new Cidade("SP");
        Cidade poa = new Cidade("POA");

        // Callbacks das cidades
        CallbackSensor callbackBsb = bsb::receberDados;
        CallbackSensor callbackRj = rj::receberDados;
        CallbackSensor callbackSjc = sjc::receberDados;
        CallbackSensor callbackSp = sp::receberDados;
        CallbackSensor callbackPoa = poa::receberDados;

        System.out.println("TESTE 1: cadastro de callbacks no sensor 1");
        sensor1.adicionarCallback(callbackBsb);
        sensor1.adicionarCallback(callbackRj);
        sensor1.adicionarCallback(callbackSp);
        sensor1.setDados(32.5, 6.8, 85.0);

        System.out.println("\nTESTE 2: cadastro de callbacks no sensor 2");
        sensor2.adicionarCallback(callbackSjc);
        sensor2.adicionarCallback(callbackPoa);
        sensor2.adicionarCallback(callbackSp);
        sensor2.setDados(28.3, 7.1, 90.0);

        System.out.println("\nTESTE 3: remoção de callback");
        sensor1.removerCallback(callbackRj);
        sensor1.setDados(30.0, 6.5, 80.0);

        System.out.println("Verificação esperada: a cidade RJ não deve aparecer nesse último teste.");

        System.out.println("\nTESTE 4: novo valor no sensor 2");
        sensor2.setDados(26.7, 7.4, 93.0);

        System.out.println("Verificação esperada: SJC, POA e SP devem receber os novos dados.");
    }
}