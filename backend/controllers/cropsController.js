const Crop = require('../models/crops');

// Obtener todos los cultivos del usuario
const getCrops = async (req, res) => {
    try {
        const userId = req.user.id;
        const crops = await Crop.find({ user: userId });
        res.json(crops);
    } catch (error) {
        res.status(500).json({ message: 'Error al obtener cultivos' });
    }
};

// Obtener un cultivo por ID
const getCropById = async (req, res) => {
    try {
        const cropId = req.params.id;
        const crop = await Crop.findOne({ _id: cropId, user: req.user.id });

        if (!crop) {
            return res.status(404).json({ message: 'Cultivo no encontrado' });
        }

        res.json(crop);
    } catch (error) {
        res.status(500).json({ message: 'Error al obtener el cultivo' });
    }
};

// Crear un nuevo cultivo
const addCrop = async (req, res) => {
    try {
        const newCrop = new Crop({
            name: req.body.name,
            plantedDate: req.body.plantedDate,
            user: req.user.id, // Asociar el cultivo con el usuario autenticado
            location: req.body.location,
            soilType: req.body.soilType,
            cropType: req.body.cropType,
            notes: req.body.notes
        });

        await newCrop.save();
        res.status(201).json(newCrop);
    } catch (error) {
        res.status(400).json({ message: 'Error al crear el cultivo' });
    }
};

// Actualizar un cultivo existente
const updateCrop = async (req, res) => {
    try {
        const cropId = req.params.id;
        const crop = await Crop.findOne({ _id: cropId, user: req.user.id });

        if (!crop) {
            return res.status(404).json({ message: 'Cultivo no encontrado' });
        }

        crop.name = req.body.name || crop.name;
        crop.plantedDate = req.body.plantedDate || crop.plantedDate;
        crop.location = req.body.location || crop.location;
        crop.soilType = req.body.soilType || crop.soilType;
        crop.cropType = req.body.cropType || crop.cropType;
        crop.notes = req.body.notes || crop.notes;

        await crop.save();
        res.json(crop);
    } catch (error) {
        res.status(400).json({ message: 'Error al actualizar el cultivo' });
    }
};

// Eliminar un cultivo
const deleteCrop = async (req, res) => {
    try {
        const cropId = req.params.id;
        const crop = await Crop.findOneAndDelete({ _id: cropId, user: req.user.id });

        if (!crop) {
            return res.status(404).json({ message: 'Cultivo no encontrado' });
        }



        
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
