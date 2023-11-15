// ***********************************************
// This example commands.js shows you how to
// create various custom commands and overwrite
// existing commands.
//
// For more comprehensive examples of custom
// commands please read more here:
// https://on.cypress.io/custom-commands
// ***********************************************
//
//
// -- This is a parent command --
// Cypress.Commands.add('login', (email, password) => { ... })
//
//
// -- This is a child command --
// Cypress.Commands.add('drag', { prevSubject: 'element'}, (subject, options) => { ... })
//
//
// -- This is a dual command --
// Cypress.Commands.add('dismiss', { prevSubject: 'optional'}, (subject, options) => { ... })
//
//
// -- This will overwrite an existing command --
// Cypress.Commands.overwrite('visit', (originalFn, url, options) => { ... })


Cypress.Commands.add('deleteUsers', () => {
  cy.request({
    url: '/test/users',
    method: 'delete'
  }).then(({body}) => {
    expect(body.result).to.eq('Done');
  });
});

Cypress.Commands.add('createTestAdmin', () => {
  cy.request({
    url: `/test/create-admin`,
    method: 'post'
  }).then(({body}) => {
    expect(body.result).to.eq('Done');
  });
});

Cypress.Commands.add('deleteSigns', () => {
  cy.request({
    url: '/test/signs',
    method: 'delete'
  }).then(({body}) => {
    expect(body.result).to.eq('Done');
  });
});

Cypress.Commands.add('createTestSign', () => cy.request({
  url: `/test/create-sign`,
  method: 'post'
}));

