const express = require('express');
const bodyParser = require('body-parser');
const connectDB = require('./config/db.config');
const userRoutes = require('./routes/userRoutes');
const adminRoutes = require('./routes/adminRoutes');
const cropRoutes = require('./routes/cropRoutes');
const sensorDataRoutes = require('./routes/sensorRoutes');
const pestLogRoutes = require('./routes/pestRoutes');
const loginRoutes = require('./routes/loginRoutes');
const commentRoutes = require('./routes/commentRoutes');
require('dotenv').config(); 

const app = express();
const port = process.env.PORT || 3000;

connectDB();

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

app.use('/api/users', userRoutes);
app.use('/api/admins', adminRoutes);
app.use('/api/crops', cropRoutes);
app.use('/api/sensor-data', sensorDataRoutes);
app.use('/api/pest-logs', pestLogRoutes);
app.use('/api', loginRoutes);
app.use('/api/comments', commentRoutes); // Añadir las rutas de comentarios

app.listen(port, () => {
    console.log(`SmartGreen API listening at http://localhost:${port}`);
});
