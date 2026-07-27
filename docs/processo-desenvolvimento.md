# Processo de desenvolvimento do KDS

## 1. Objetivo

Este documento define como o trabalho de conclusão do KDS será planejado,
executado, validado e registrado. O processo busca:

- manter o escopo do produto sob controle;
- tornar decisões e entregas rastreáveis;
- preservar a qualidade das branches principais;
- integrar o trabalho dos repositórios de backend e frontend;
- demonstrar práticas de Scrum, Git e DevOps de forma verificável;
- permitir inspeção e adaptação ao final de cada sprint.

O processo é aplicado a:

- backend: [XundaBoy/KDS](https://github.com/XundaBoy/KDS);
- frontend: [BrunoLgN/kds-front](https://github.com/BrunoLgN/kds-front);
- planejamento integrado: GitHub Project `KDS - Scrum`.

## 2. Contexto e adaptação do Scrum

O KDS é desenvolvido individualmente e utiliza um processo inspirado no Scrum.
Não há um Scrum Team completo com pessoas diferentes exercendo todas as
responsabilidades previstas pelo framework. No contexto do projeto, o mesmo
desenvolvedor assume responsabilidades de produto, desenvolvimento e melhoria
do processo.

São mantidos os elementos que geram transparência, inspeção e adaptação:

- Product Backlog representado por issues;
- Sprint Goal;
- Sprint Backlog;
- sprints com duração fixa;
- planejamento da sprint;
- acompanhamento diário;
- Sprint Review;
- Sprint Retrospective;
- Definition of Done.

As adaptações para o trabalho individual são:

- a atualização diária é assíncrona e registrada no GitHub Project;
- Planning, Review e Retrospective são registros objetivos, não reuniões
  artificiais;
- a Definition of Ready é usada como prática complementar para reduzir
  ambiguidades, sem funcionar como barreira rígida;
- Story Points são usados como medida relativa de esforço, complexidade e
  incerteza, embora não sejam exigidos pelo Scrum.

## 3. Fontes de verdade

Cada informação deve ser mantida no local apropriado:

| Informação | Fonte de verdade |
| --- | --- |
| Código do backend | Repositório `XundaBoy/KDS` |
| Código do frontend | Repositório `BrunoLgN/kds-front` |
| Trabalho planejado | Issues dos respectivos repositórios |
| Estado do trabalho | GitHub Project `KDS - Scrum` |
| Discussão e validação de mudanças | Pull Requests |
| Decisões e documentação geral | Diretório `docs/` do backend |
| Documentação específica do frontend | Diretório `docs/` do frontend |

Uma decisão relevante não deve existir somente em conversas, anotações locais
ou mensagens de commit.

## 4. Organização das sprints

### 4.1 Duração

As sprints têm duração padrão de uma semana. A duração poderá ser revisada em
uma retrospectiva, mas não deve ser alterada durante uma sprint em andamento.

### 4.2 Sprint Goal

Cada sprint deve possuir um objetivo único e compreensível. O Sprint Goal
explica o resultado que se pretende alcançar, e não apenas a lista de tarefas.

O Sprint Goal deve ser registrado no status do GitHub Project no início da
sprint.

### 4.3 Seleção do trabalho

Durante o planejamento:

1. o backlog é ordenado por prioridade;
2. somente issues que atendem à Definition of Ready são consideradas;
3. a capacidade é estimada usando a velocidade observada nas sprints
   anteriores;
4. as issues selecionadas recebem o valor do campo `Sprint`;
5. o conjunto selecionado deve contribuir para o Sprint Goal.

Na primeira sprint, a capacidade é uma hipótese. A quantidade concluída será
usada como referência inicial, sem transformar velocidade em meta de
produtividade.

### 4.4 Trabalho não concluído

Uma issue não concluída não recebe pontos parciais e não é considerada `Done`.
Ao final da sprint, ela retorna ao backlog para ser reavaliada e reestimada
quando necessário. Sua inclusão na sprint seguinte não é automática.

## 5. Eventos e registros da sprint

### 5.1 Sprint Planning

O Planning inicia a sprint e deve registrar:

- período da sprint;
- Sprint Goal;
- issues selecionadas;
- total de Story Points;
- dependências ou riscos conhecidos.

### 5.2 Acompanhamento diário

O acompanhamento diário verifica o progresso em direção ao Sprint Goal. A
atualização deve ser curta e responder:

- o que avançou desde a última atualização;
- qual é o próximo passo;
- existe algum bloqueio;
- o plano da sprint precisa ser ajustado.

O status das issues no Project deve refletir o estado real do trabalho.

### 5.3 Sprint Review

A Review inspeciona o resultado entregue. Deve registrar:

- se o Sprint Goal foi atingido;
- issues concluídas e não concluídas;
- evidências das entregas;
- builds, testes ou demonstrações executados;
- feedback obtido;
- mudanças necessárias no backlog.

Trabalho que não atende à Definition of Done pode ser apresentado como
aprendizado, mas não é contabilizado como concluído.

### 5.4 Sprint Retrospective

A Retrospective inspeciona o processo de trabalho. Deve responder:

- o que funcionou;
- o que dificultou o trabalho;
- quais hipóteses estavam incorretas;
- qual melhoria concreta será aplicada na próxima sprint.

A retrospectiva deve produzir no máximo poucas ações de melhoria, priorizando
mudanças que possam ser verificadas na sprint seguinte.

## 6. Fluxo do GitHub Project

O fluxo padrão é:

```text
Backlog -> Ready -> In Progress -> In Review -> Done
```

| Status | Significado | Condição para saída |
| --- | --- | --- |
| `Backlog` | Trabalho identificado, ainda não preparado ou selecionado | Issue refinada e atendendo à Definition of Ready |
| `Ready` | Trabalho preparado e disponível para execução | Início efetivo do trabalho |
| `In Progress` | Trabalho em execução em uma branch própria | Pull Request aberto com a entrega completa |
| `In Review` | Mudança em revisão e validação | PR aprovado, verificações concluídas e merge realizado |
| `Done` | Entrega integrada e atendendo à Definition of Done | Estado final |

Issues bloqueadas permanecem no status que representa seu estado real e devem
registrar o bloqueio em comentário. Um bloqueio não deve ser escondido por uma
mudança artificial de status.

### 6.1 Limite de trabalho em andamento

O limite padrão é uma issue em `In Progress`. Uma nova issue só deve ser
iniciada quando a anterior estiver em `In Review` ou `Done`.

O limite reduz troca de contexto e favorece a conclusão de entregas.

## 7. Priorização

| Prioridade | Uso |
| --- | --- |
| `P0` | Bloqueia a continuidade do projeto ou é indispensável para o Sprint Goal |
| `P1` | Alta importância e deve ser tratada nas próximas sprints |
| `P2` | Importante, mas não bloqueia o fluxo principal |
| `P3` | Melhoria de baixa urgência ou conveniência |

A prioridade representa valor e urgência, não tamanho. Uma issue pequena pode
ter baixa prioridade, e uma issue grande pode ser indispensável.

## 8. Estimativas e Story Points

O KDS utiliza a escala:

```text
1, 2, 3, 5, 8
```

Os pontos combinam:

- esforço;
- complexidade;
- incerteza;
- risco;
- quantidade de validação necessária.

Eles não representam horas. Uma issue de 8 pontos deve ser reavaliada e, quando
possível, dividida em entregas menores.

A velocidade corresponde à soma dos pontos das issues que chegaram a `Done`
durante a sprint. Ela auxilia o planejamento futuro e não deve ser usada como
meta individual ou medida isolada de produtividade.

## 9. Definition of Ready

A Definition of Ready é uma prática complementar adotada para melhorar a
qualidade do backlog. Ela deve orientar o refinamento sem impedir descoberta e
aprendizado.

Uma issue está pronta quando:

- [ ] possui contexto e objetivo;
- [ ] identifica um entregável;
- [ ] apresenta critérios de aceitação verificáveis;
- [ ] explicita o que está fora do escopo;
- [ ] identifica o repositório afetado;
- [ ] possui dependências e bloqueios conhecidos;
- [ ] possui prioridade, área e Story Points;
- [ ] pode ser concluída dentro de uma sprint;
- [ ] não depende de uma decisão essencial ainda não tomada.

## 10. Definition of Done

A Definition of Done estabelece o nível mínimo de qualidade comum a todas as
entregas.

Uma issue está concluída quando:

- [ ] todos os critérios de aceitação foram atendidos;
- [ ] a alteração foi realizada em uma branch específica;
- [ ] os commits seguem a convenção definida;
- [ ] existe um Pull Request relacionado à issue;
- [ ] o diff do PR foi revisado;
- [ ] build e testes aplicáveis foram executados;
- [ ] o CI está aprovado quando disponível;
- [ ] a documentação relacionada foi atualizada;
- [ ] nenhum segredo, credencial real ou arquivo local indevido foi versionado;
- [ ] as evidências de validação estão registradas no PR;
- [ ] o merge foi realizado na branch principal;
- [ ] a issue foi encerrada;
- [ ] o item correspondente foi movido para `Done`;
- [ ] a branch de trabalho foi removida após o merge.

Critérios que não se aplicam devem ser marcados como não aplicáveis e
justificados no PR; não devem ser silenciosamente ignorados.

## 11. Fluxo Git

O projeto utiliza um GitHub Flow leve:

```text
Issue
  -> branch curta
  -> alterações
  -> commits
  -> push
  -> Pull Request
  -> revisão e validação
  -> merge
  -> exclusão da branch
```

Não são usadas branches permanentes `develop` ou `release`. Cada repositório
parte de sua branch principal atual:

- backend: `master`;
- frontend: `main`.

Antes de iniciar uma issue:

```bash
git switch <branch-principal>
git pull --ff-only origin <branch-principal>
git switch -c <tipo>/<numero-da-issue>-<descricao-curta>
```

Uma branch deve tratar uma única issue. Exceções precisam estar justificadas no
PR e todas as issues relacionadas devem ser vinculadas.

## 12. Padrão de branches

O formato é:

```text
<tipo>/<numero-da-issue>-<descricao-curta>
```

| Tipo | Uso |
| --- | --- |
| `feat` | Novo comportamento relativo ao estado atual do código |
| `fix` | Correção de defeito |
| `docs` | Documentação |
| `test` | Testes |
| `ci` | Integração e entrega contínuas |
| `refactor` | Mudança interna sem alterar o comportamento esperado |
| `chore` | Manutenção, ferramentas e configurações |

Exemplos:

```text
docs/5-processo-desenvolvimento
feat/12-autenticacao-keycloak
fix/23-update-cidade
ci/40-pipeline-frontend
```

O tipo `feat` indica comportamento novo em relação ao código existente. Ele
pode ser usado para concluir uma funcionalidade já prevista no escopo
congelado, mas não autoriza expansão de escopo.

## 13. Padrão de commits

Os commits seguem Conventional Commits:

```text
<tipo>[escopo-opcional]: <descricao>
```

Exemplos:

```text
docs: define processo de desenvolvimento
feat(auth): integra backend ao Keycloak
fix(cidade): corrige atualizacao de cadastro
test(troca): adiciona testes de integracao
ci(frontend): adiciona pipeline de build
```

As mensagens devem:

- descrever a intenção da mudança;
- usar linguagem objetiva;
- evitar mensagens genéricas como `ajustes`, `teste` ou `alteracoes`;
- manter cada commit coerente e revisável.

## 14. Fluxo de Pull Requests

Um PR deve conter:

- resumo do que foi alterado;
- motivo da alteração;
- relação com os critérios de aceitação;
- comandos e resultados de validação;
- riscos, limitações ou itens não aplicáveis;
- referência de fechamento, como `Closes #5`.

O PR passa pelas etapas:

1. revisão do próprio diff;
2. execução das validações aplicáveis;
3. aprovação dos checks disponíveis;
4. correção de pendências;
5. merge;
6. exclusão da branch remota;
7. atualização local da branch principal.

Quando disponível, `Squash and merge` é o método preferido para manter um
commit final coerente por PR. O título final deve seguir Conventional Commits.
O PR preserva discussão, commits intermediários, checks e arquivos alterados.

Após o merge:

```bash
git switch <branch-principal>
git pull --ff-only origin <branch-principal>
git branch -d <branch-de-trabalho>
```

## 15. Integração entre os repositórios

As issues devem ser criadas no repositório que receberá a alteração:

- mudanças do backend e documentação geral: `XundaBoy/KDS`;
- mudanças específicas do frontend: `BrunoLgN/kds-front`.

Todas as issues selecionadas para trabalho são adicionadas ao mesmo GitHub
Project. Quando uma entrega afeta os dois repositórios:

1. deve existir uma issue em cada repositório;
2. as issues devem conter links recíprocos;
3. dependências e ordem de implementação devem ser explícitas;
4. cada repositório mantém sua própria branch e seu próprio PR;
5. a entrega só é considerada integrada após validação dos dois lados.

Referências entre repositórios devem usar a URL completa da issue ou do PR para
evitar ambiguidade.

## 16. Mudanças de escopo

O escopo de conclusão do KDS permanece congelado durante a sprint. Uma ideia ou
solicitação nova:

1. é registrada como issue no backlog;
2. tem valor, impacto e dependências avaliados;
3. é comparada ao escopo definido do produto;
4. não entra automaticamente na sprint em andamento.

Descobertas necessárias para completar uma funcionalidade existente não devem
ser implementadas silenciosamente. Elas são registradas como issues e
priorizadas.

Uma expansão real de escopo exige decisão explícita e atualização da
documentação do produto antes do desenvolvimento.

## 17. Registro da Review e da Retrospective

### 17.1 Modelo de Sprint Review

```markdown
## Sprint Review

- Sprint Goal:
- Resultado do Sprint Goal:
- Issues concluídas:
- Issues não concluídas:
- Story Points planejados:
- Story Points concluídos:
- Evidências e demonstrações:
- Feedback:
- Ajustes necessários no backlog:
```

### 17.2 Modelo de Sprint Retrospective

```markdown
## Sprint Retrospective

- O que funcionou:
- O que dificultou:
- O que foi aprendido:
- Hipóteses que precisam ser revistas:
- Uma melhoria para a próxima sprint:
```

Os registros podem ser publicados como atualização do GitHub Project ou em
documento próprio da sprint, desde que permaneçam acessíveis e versionados ou
rastreáveis.

## 18. Manutenção deste processo

O processo deve ser inspecionado nas retrospectivas. Mudanças devem resolver um
problema observado, ser registradas por issue e passar pelo mesmo fluxo de
branch e PR.

O documento não deve ser alterado apenas para descrever uma prática idealizada;
ele deve refletir o processo realmente utilizado no KDS.

## 19. Referências

- [The Scrum Guide - versão oficial atual](https://scrumguides.org/download.html)
- [Guia do Scrum 2020 em português](https://scrumguides.org/docs/scrumguide/v2020/2020-Scrum-Guide-Portuguese-European.pdf)
- [Scrum.org - Definition of Done](https://www.scrum.org/resources/definition-done)
- [Scrum.org - Definition of Ready](https://www.scrum.org/resources/blog/ready-or-not-demystifying-definition-ready-scrum)
- [GitHub Docs - GitHub Flow](https://docs.github.com/en/get-started/using-github/github-flow)
- [GitHub Docs - Pull Requests](https://docs.github.com/en/pull-requests/get-started/about-pull-requests)
- [Conventional Commits 1.0.0](https://www.conventionalcommits.org/en/v1.0.0/)
