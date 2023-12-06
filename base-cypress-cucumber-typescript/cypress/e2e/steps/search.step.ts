import { Given, When, Then } from '@badeball/cypress-cucumber-preprocessor'

import MemedroidPage from './src/pages/MemedroidPage'

Given('I go to the Memedroid page', function () {
  this.browser = new MemedroidPage()
  this.browser.load()
})
