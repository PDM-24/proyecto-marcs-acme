const express = require('express');
const router = express.Router();
const { getSensorData, getSensorDataById, addSensorData, updateSensorData, deleteSensorData } = require('../controllers/sensorController');

router.get('/', getSensorData);
router.get('/:id', getSensorDataById);
router.post('/add', addSensorData);
router.put('/:id', updateSensorData);
router.delete('/:id', deleteSensorData);

module.exports = router;
