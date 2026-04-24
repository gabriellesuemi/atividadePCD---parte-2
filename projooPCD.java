import java.util.ArrayList;
import java.util.List;

/*
Foi criada a interface CallbackSensor para substituir a antiga interface Observador.

Antes, o SensorAmazonia dependia diretamente de objetos Observador.
Agora, ele trabalha com callbacks (funções que serão executadas quando os dados forem atualizados).

Isso aplica Inversão de Controle (IoC), pois o sensor não precisa mais conhecer
quem recebe os dados, apenas executa os callbacks registrados.
*/
interface CallbackSensor {
    void executar(String nomeSensor, double temperatura, double ph, double umidade);
}

/*
A interface Sujeito continua com a mesma função de gerenciar os inscritos,
mas agora os métodos trabalham com CallbackSensor em vez de Observador.
*/
interface Sujeito {
    void adicionarCallback(CallbackSensor callback);
    void removerCallback(CallbackSensor callback);
    void notificarCallbacks();
}

class SensorAmazonia implements Sujeito {
    private String nomeSensor;
    private double temperatura;
    private double ph;
    private double umidade;

    /*
    A lista de Observador foi substituída por uma lista de CallbackSensor.

    Antes:
    private List<Observador> observadores;

    Agora:
    private List<CallbackSensor> callbacks;

    Isso reduz o acoplamento, pois o sensor não depende mais diretamente
    da classe Cidade nem da interface Observador.
    */
    private List<CallbackSensor> callbacks;

    public SensorAmazonia(String nomeSensor) {
        this.nomeSensor = nomeSensor;
        this.callbacks = new ArrayList<>();
    }

    @Override
    public void adicionarCallback(CallbackSensor callback) {
        callbacks.add(callback);
    }

    @Override
    public void removerCallback(CallbackSensor callback) {
        callbacks.remove(callback);
    }

    @Override
    public void notificarCallbacks() {
        /*
        O método agora executa callbacks em vez de chamar atualizar() de Observador.

        O sensor apenas dispara a ação cadastrada, sem saber quem irá recebê-la.
        Isso caracteriza Callback + Inversão de Controle.
        */
        for (CallbackSensor callback : callbacks) {
            callback.executar(nomeSensor, temperatura, ph, umidade);
        }
    }

    public void setDados(double temperatura, double ph, double umidade) {
        this.temperatura = temperatura;
        this.ph = ph;
        this.umidade = umidade;

        notificarCallbacks();
    }
}

class Cidade {
    private String nome;

    public Cidade(String nome) {
        this.nome = nome;
    }

    /*
    A classe Cidade não implementa mais a interface Observador.
    Dessa forma, a cidade entrega sua própria ação para ser executada,
    sem que o sensor precise conhecê-la diretamente.
    */
    public void receberDados(String nomeSensor, double temperatura, double ph, double umidade) {
        System.out.println("Cidade: " + nome);
        System.out.println("Sensor: " + nomeSensor);
        System.out.println("Temperatura: " + temperatura + " °C");
        System.out.println("pH: " + ph);
        System.out.println("Umidade do ar: " + umidade + " %");
        System.out.println("----------------------------------");
    }
}