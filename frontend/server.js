import express from 'express';
import cors from 'cors';

const app = express();

app.use(cors());
app.use(express.json());

app.post('/api/auth/login', (req, res) => {
    res.json({ token: 'dummy-token' });
  });
  

app.get('/', (req, res) => {
    res.send('Server is running!');
});

app.listen(8080, () => {
    console.log('Server is running on http://localhost:8080');
  });