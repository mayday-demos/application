Questo progetto ha l'obiettivo di implementare una pipeline completa per il versionamento, la build e il deploy di un'applicazione Spring Boot su un cluster Kubernetes utilizzando Helm.

## Obiettivo Principale

L'obiettivo principale è configurare una pipeline automatizzata che includa le seguenti fasi:

1. **Versionamento**: Gestione delle versioni dell'applicazione e della chart di Helm.
   
2. **Build**: Compilazione dell'applicazione Spring Boot e creazione di un'immagine Docker.
   
3. **Deploy**: Utilizzo di Helm per distribuire l'applicazione su un ambiente Kubernetes clusterizzato.

### Dettagli dell'applicazione

- **Tecnologia**: Applicazione basata su Spring Boot.
- **Accesso**: Accessibile tramite browser.
- **Configurazione esterna**: Utilizza un file di configurazione esterno gestito tramite Helm per la visualizzazione di una stringa dinamica.

## Obiettivo Bonus

Come obiettivo bonus, il progetto prevede la creazione di una pipeline avanzata che:

- Utilizza il Chart Helm ottenuto nel passo precedente come "Chart di prodotto".
- Permette di sovrascrivere la stringa di configurazione dinamica e di aggiornare l'applicazione sul browser senza modificare direttamente il Chart di prodotto.

## obiettivo
Ho deciso di completare il progetto con l'obiettivo bonus.

## Setup dell'Ambiente di Sviluppo

Per lo sviluppo e l'esecuzione delle pipeline, l'ambiente utilizzato comprende:

- **Kind**: Utilizzato per creare e gestire un cluster Kubernetes locale.
  - Setup di Kind in Jenkins: Configurazione di un nuovo cloud in Jenkins con la kubeconfig di Kind.
    ```bash
    kind get kubeconfig --internal > .kube/kind
    ```

- **GitHub**: Repository utilizzato per il versionamento del codice sorgente e dei manifesti Helm.
  - La configurazione CI è disponibile nel file `ci/pipeline.Jenkinsfile` nel repository dell'applicativo.

- **Docker Hub (docker.io)**: Repository per il push delle immagini Docker generate durante il processo di build.

- **Java Development Kit (JDK)**: Installato per la compilazione dell'applicazione Spring Boot.

## Flusso di Lavoro

Il flusso di lavoro della pipeline è il seguente:

1. **Trigger del Build**: Jenkins monitora il repository GitHub dell'applicazione per i cambiamenti (push o PR).
   
2. **Build dell'Applicazione**: Jenkins esegue la build dell'applicazione Spring Boot e crea un'immagine Docker.
   
3. **Push dell'Immagine**: L'immagine Docker viene pushata su Docker Hub (`docker.io`).

4. **Deploy dell'Applicazione su Kubernetes**:
   - Jenkins configura il repository GitHub della Chart Helm.
   - Configura la variabile dinamica `DYNAMIC_STRING` (parametrizzata su Jenkins) e imposta la versione dell'immagine nell'`values.yaml` della Chart Helm.
   - Utilizza Helm per installare la Chart sul cluster Kubernetes (kind).

## Note Aggiuntive

Il progetto è sviluppato utilizzando principi di continuous integration e continuous deployment (CI/CD) localmente, senza l'utilizzo di servizi PaaS su internet. Tutti gli strumenti utilizzati sono standard di mercato e open-source.

Per ulteriori dettagli su come configurare e eseguire la pipeline, consultare la documentazione di Jenkins e Helm.

