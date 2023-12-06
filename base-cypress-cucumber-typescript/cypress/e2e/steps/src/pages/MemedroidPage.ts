export default class MemedroidPage {
  private _url = 'https://es.memedroid.com/memes/tag/pagina+web'

  private _inputSearch = '#search-form-input'
  private _resultSearch = '#tags-gallery-section'

  load(): void {
    cy.visit(this._url)
    cy.get('body').should('be.visible')
  }

}
