const express = require('express')
const app = express()
const port = 3000

app.get('/', (req, res) => {
  console.log('Hello World from console!')
  res.send('Hello World!')
})

app.get('/about', (req, res) => {
  console.log('/about')
  res.send('This is a hello world demo for express js')
})

app.get('/users/:name', (req, res) => {
  console.log(req.params)
  res.send('Hello ' + req.params['name'])
})

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`)
})
