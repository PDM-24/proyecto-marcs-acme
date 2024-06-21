const pestLogs = require('../data/pestLogs');

// Obtener todos los registros de plagas
const getPestLogs = (req, res) => {
    try {
        res.json(pestLogs);
    } catch (error) {
        res.status(500).json({ message: 'Error al obtener registros de plagas' });
    }
};

// Obtener un registro de plagas por ID
const getPestLogById = (req, res) => {
    try {
        const logId = parseInt(req.params.id);
        const log = pestLogs.find(l => l.id === logId);

        if (!log) {
            return res.status(404).json({ message: 'Registro de plagas no encontrado' });
        }

        res.json(log);
    } catch (error) {
        res.status(500).json({ message: 'Error al obtener registro de plagas' });
    }
};

// Crear un nuevo registro de plagas
const addPestLog = (req, res) => {
    try {
        const newLog = {
            id: pestLogs.length + 1,
            crop: req.body.crop,
            description: req.body.description,
            date: req.body.date,
            actionsTaken: req.body.actionsTaken
        };

        pestLogs.push(newLog);
        res.status(201).json(newLog);
    } catch (error) {
        res.status(400).json({ message: 'Error al crear registro de plagas' });
    }
};

// Actualizar un registro de plagas existente
const updatePestLog = (req, res) => {
    try {
        const logId = parseInt(req.params.id);
        const log = pestLogs.find(l => l.id === logId);

        if (!log) {
            return res.status(404).json({ message: 'Registro de plagas no encontrado' });
        }

        log.crop = req.body.crop || log.crop;
        log.description = req.body.description || log.description;
        log.date = req.body.date || log.date;
        log.actionsTaken = req.body.actionsTaken || log.actionsTaken;

        res.json(log);
    } catch (error) {
        res.status(400).json({ message: 'Error al actualizar registro de plagas' });
    }
};

// Eliminar un registro de plagas
const deletePestLog = (req, res) => {
    try {
        const logId = parseInt(req.params.id);
        const logIndex = pestLogs.findIndex(l => l.id === logId);

        if (logIndex === -1) {
            return res.status(404).json({ message: 'Registro de plagas no encontrado' });
        }

        pestLogs.splice(logIndex, 1);
        res.json({ message: 'Registro de plagas eliminado' });
    } catch (error) {
        res.status(500).json({ message: 'Error al eliminar registro de plagas' });
    }
};

module.exports = {
    getPestLogs,
    getPestLogById,
    addPestLog,
    updatePestLog,
    deletePestLog
};
