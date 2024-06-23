const crops = require('../data/crops');

// Obtener todos los cultivos
const getCrops = (req, res) => {
    try {
        res.json(crops);
    } catch (error) {
        res.status(500).json({ message: 'Error al obtener cultivos' });
    }
};

// Obtener un cultivo por ID
const getCropById = (req, res) => {
    try {
        const cropId = parseInt(req.params.id);
        const crop = crops.find(c => c.id === cropId);

        if (!crop) {
            return res.status(404).json({ message: 'Cultivo no encontrado' });
        }

        res.json(crop);
    } catch (error) {
        res.status(500).json({ message: 'Error al obtener el cultivo' });
    }
};

// Crear un nuevo cultivo
const addCrop = (req, res) => {
    try {
        const newCrop = {
            id: crops.length + 1,
            name: req.body.name,
            plantedDate: req.body.plantedDate,
            user: req.body.user,
            notes: req.body.notes
        };

        crops.push(newCrop);
        res.status(201).json(newCrop);
    } catch (error) {
        res.status(400).json({ message: 'Error al crear el cultivo' });
    }
};

// Actualizar un cultivo existente
const updateCrop = (req, res) => {
    try {
        const cropId = parseInt(req.params.id);
        const crop = crops.find(c => c.id === cropId);

        if (!crop) {
            return res.status(404).json({ message: 'Cultivo no encontrado' });
        }

        crop.name = req.body.name || crop.name;
        crop.plantedDate = req.body.plantedDate || crop.plantedDate;
        crop.notes = req.body.notes || crop.notes;

        res.json(crop);
    } catch (error) {
        res.status(400).json({ message: 'Error al actualizar el cultivo' });
    }
};

// Eliminar un cultivo
const deleteCrop = (req, res) => {
    try {
        const cropId = parseInt(req.params.id);
        const cropIndex = crops.findIndex(c => c.id === cropId);

        if (cropIndex === -1) {
            return res.status(404).json({ message: 'Cultivo no encontrado' });
        }

        crops.splice(cropIndex, 1);
        res.json({ message: 'Cultivo eliminado' });
    } catch (error) {
        res.status(500).json({ message: 'Error al eliminar el cultivo' });
    }
};

module.exports = {
    getCrops,
    getCropById,
    addCrop,
    updateCrop,
    deleteCrop
};
