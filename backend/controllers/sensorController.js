const sensorsData = require('../data/sensorsData');

// Obtener todos los datos de sensores
const getSensorData = (req, res) => {
    try {
        res.json(sensorsData);
    } catch (error) {
        res.status(500).json({ message: 'Error al obtener datos de sensores' });
    }
};

// Obtener datos de un sensor por ID
const getSensorDataById = (req, res) => {
    try {
        const dataId = parseInt(req.params.id);
        const data = sensorsData.find(d => d.id === dataId);

        if (!data) {
            return res.status(404).json({ message: 'Datos del sensor no encontrados' });
        }

        res.json(data);
    } catch (error) {
        res.status(500).json({ message: 'Error al obtener datos del sensor' });
    }
};

// Crear nuevos datos de sensor
const addSensorData = (req, res) => {
    try {
        const newData = {
            id: sensorsData.length + 1,
            sensor_id: req.body.sensor_id,
            type: req.body.type,
            value: req.body.value,
            timestamp: req.body.timestamp
        };

        sensorsData.push(newData);
        res.status(201).json(newData);
    } catch (error) {
        res.status(400).json({ message: 'Error al crear datos del sensor' });
    }
};

// Actualizar datos de sensor existentes
const updateSensorData = (req, res) => {
    try {
        const dataId = parseInt(req.params.id);
        const data = sensorsData.find(d => d.id === dataId);

        if (!data) {
            return res.status(404).json({ message: 'Datos del sensor no encontrados' });
        }

        data.sensor_id = req.body.sensor_id || data.sensor_id;
        data.type = req.body.type || data.type;
        data.value = req.body.value || data.value;
        data.timestamp = req.body.timestamp || data.timestamp;

        res.json(data);
    } catch (error) {
        res.status(400).json({ message: 'Error al actualizar datos del sensor' });
    }
};

// Eliminar datos de sensor
const deleteSensorData = (req, res) => {
    try {
        const dataId = parseInt(req.params.id);
        const dataIndex = sensorsData.findIndex(d => d.id === dataId);

        if (dataIndex === -1) {
            return res.status(404).json({ message: 'Datos del sensor no encontrados' });
        }

        sensorsData.splice(dataIndex, 1);
        res.json({ message: 'Datos del sensor eliminados' });
    } catch (error) {
        res.status(500).json({ message: 'Error al eliminar datos del sensor' });
    }
};

module.exports = {
    getSensorData,
    getSensorDataById,
    addSensorData,
    updateSensorData,
    deleteSensorData
};
